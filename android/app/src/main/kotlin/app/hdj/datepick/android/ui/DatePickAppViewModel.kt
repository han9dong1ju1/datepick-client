package app.hdj.datepick.android.ui

import androidx.compose.runtime.compositionLocalOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.hdj.datepick.android.ui.DatePickAppViewModelDelegate.*
import app.hdj.datepick.domain.Authenticator
import app.hdj.datepick.domain.model.settings.AppTheme
import app.hdj.datepick.domain.model.user.User
import app.hdj.datepick.domain.repository.SettingsRepository
import app.hdj.datepick.domain.usecase.invoke
import app.hdj.datepick.domain.usecase.user.GetLatestMeUseCase
import app.hdj.datepick.domain.usecase.user.ObserveMeUseCase
import app.hdj.datepick.ui.utils.ViewModelDelegate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

val LocalDatePickAppViewModel = compositionLocalOf<DatePickAppViewModelDelegate> {
    error("Not Provided")
}

interface DatePickAppViewModelDelegate : ViewModelDelegate<State, Effect, Event> {

    data class State(
        val me: User? = null,
        val appTheme: AppTheme = AppTheme.System
    )

    sealed class Effect {

    }

    sealed class Event {

        object ReloadContents : Event()


    }

}

@HiltViewModel
@OptIn(FlowPreview::class)
class DatePickAppViewModel @Inject constructor(
    private val authenticator: Authenticator,
    settingsRepository: SettingsRepository,
    getLatestMeUseCase: GetLatestMeUseCase,
    observeMeUseCase: ObserveMeUseCase
) : ViewModel(), DatePickAppViewModelDelegate {

    private val me = observeMeUseCase()

    override val state: StateFlow<State> =
        combine(me, settingsRepository.theme) { me, theme ->
            State(me, theme)
        }.stateIn(viewModelScope, SharingStarted.Lazily, State())

    init {
        viewModelScope.launch {
            authenticator.getCurrentFirebaseUser()
            getLatestMeUseCase.invoke().collect()
        }
    }

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)
    override val effect: Flow<Effect> = effectChannel.receiveAsFlow()

    override fun event(event: Event) {
        viewModelScope.launch {
            when (event) {
                Event.ReloadContents -> {

                }
            }
        }
    }

}