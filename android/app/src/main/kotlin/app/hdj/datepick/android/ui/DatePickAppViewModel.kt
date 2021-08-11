package app.hdj.datepick.android.ui

import androidx.compose.runtime.compositionLocalOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.hdj.datepick.android.ui.DatePickAppViewModelDelegate.*
import app.hdj.datepick.domain.model.user.User
import app.hdj.datepick.domain.usecase.user.GetMeUseCase
import app.hdj.datepick.ui.utils.ViewModelDelegate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

val LocalDatePickAppViewModel = compositionLocalOf<DatePickAppViewModelDelegate> {
    error("Not Provided")
}

enum class StatusBarMode {
    STATUS_BAR_FORCE_WHITE,
    STATUS_BAR_SYSTEM
}

interface DatePickAppViewModelDelegate : ViewModelDelegate<State, Effect, Event> {

    data class State(
        val me: User? = null,
        val statusBarMode: StatusBarMode = StatusBarMode.STATUS_BAR_SYSTEM
    )

    sealed class Effect {

    }

    sealed class Event {

        object ReloadContents : Event()

        data class ChangeStatusBarMode(val statusBarMode: StatusBarMode) : Event()

    }

}

@HiltViewModel
@OptIn(FlowPreview::class)
class DatePickAppViewModel @Inject constructor(
    getMeUseCase: GetMeUseCase
) : ViewModel(), DatePickAppViewModelDelegate {

    private val statusBarMode = MutableStateFlow(StatusBarMode.STATUS_BAR_SYSTEM)

    private val me = getMeUseCase.observable()

    override val state: StateFlow<State> =
        combine(me, statusBarMode) { me, statusBarMode ->
            Timber.d("statusBarMode : $statusBarMode")
            State(me, statusBarMode)
        }.stateIn(viewModelScope, SharingStarted.Lazily, State())

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)
    override val effect: Flow<Effect> = effectChannel.receiveAsFlow()

    override fun event(event: Event) {
        viewModelScope.launch {
            when (event) {
                is Event.ChangeStatusBarMode -> statusBarMode.emit(event.statusBarMode)
                Event.ReloadContents -> {

                }
            }
        }
    }

}