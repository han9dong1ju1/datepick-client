package app.hdj.datepick.android.ui

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.hdj.datepick.android.ui.DatePickAppViewModelDelegate.*
import app.hdj.datepick.domain.Authenticator
import app.hdj.datepick.domain.model.user.User
import app.hdj.datepick.domain.settings.AppSettings
import app.hdj.datepick.domain.usecase.invoke
import app.hdj.datepick.domain.usecase.user.GetLatestMeUseCase
import app.hdj.datepick.domain.usecase.user.ObserveMeUseCase
import app.hdj.datepick.ui.utils.ViewModelDelegate
import app.hdj.datepick.utils.PlatformLogger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import javax.inject.Inject

val LocalDatePickAppViewModel = staticCompositionLocalOf<DatePickAppViewModelDelegate> {
    error("Not Provided")
}

interface DatePickAppViewModelDelegate : ViewModelDelegate<State, Effect, Event> {

    data class State(
        val me: User? = null,
        val appTheme: AppSettings.AppTheme? = null,
    )

    sealed class Effect {
        object ShowNetworkErrorDialog : Effect()
        object OpenMainScreen : Effect()
    }

    sealed class Event {

        class UpdateAppTheme(val appTheme: AppSettings.AppTheme) : Event()

        object TryFetchMe : Event()

    }

}

@HiltViewModel
@OptIn(FlowPreview::class)
class DatePickAppViewModel @Inject constructor(
    private val authenticator: Authenticator,
    private val appSettings: AppSettings,
    private val getLatestMeUseCase: GetLatestMeUseCase,
    observeMeUseCase: ObserveMeUseCase
) : ViewModel(), DatePickAppViewModelDelegate {

    private val me = observeMeUseCase()

    override val state: StateFlow<State> =
        combine(
            me,
            appSettings.appTheme
        ) { me, appTheme ->
            State(me, appTheme)
        }.stateIn(viewModelScope, SharingStarted.Lazily, State())

    init {
        viewModelScope.launch {
            fetchMe()
        }
    }

    private suspend fun fetchMe() {
        authenticator.runCatching {
            withTimeout(5000) { getCurrentFirebaseUser() }
        }.onSuccess {
            getLatestMeUseCase().onCompletion {
                effectChannel.send(Effect.OpenMainScreen)
            }.collect()
        }.onFailure {
            PlatformLogger.e(it)
            effectChannel.send(Effect.ShowNetworkErrorDialog)
        }
    }

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)
    override val effect: Flow<Effect> = effectChannel.receiveAsFlow()

    override fun event(event: Event) {
        viewModelScope.launch {
            when (event) {
                is Event.UpdateAppTheme -> appSettings.updateAppTheme(event.appTheme)
                Event.TryFetchMe -> fetchMe()
            }
        }
    }

}