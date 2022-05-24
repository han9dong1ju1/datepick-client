package app.hdj.datepick.presentation.profile

import app.hdj.datepick.domain.model.user.User
import app.hdj.datepick.domain.usecase.user.ObserveMeUseCase
import app.hdj.datepick.presentation.PlatformViewModel
import app.hdj.datepick.presentation.UnidirectionalViewModelDelegate
import app.hdj.datepick.presentation.profile.ProfileScreenViewModelDelegate.*
import app.hdj.datepick.utils.di.HiltViewModel
import app.hdj.datepick.utils.di.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

interface ProfileScreenViewModelDelegate : UnidirectionalViewModelDelegate<State, Effect, Event> {

    data class State(
        val me : User? = null
    )

    sealed interface Effect {

    }

    sealed interface Event {
        object Refresh : Event
    }

}

@HiltViewModel
class ProfileScreenViewModel @Inject constructor(
    observeMeUseCase: ObserveMeUseCase
) : PlatformViewModel(), ProfileScreenViewModelDelegate {

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)
    override val effect: Flow<Effect> = effectChannel.receiveAsFlow()

    override val state: StateFlow<State> = combine(observeMeUseCase(), flowOf(true)) { me, _ ->
        State(me)
    }.asStateFlow(State(), platformViewModelScope)

    override fun event(e: Event) {
        platformViewModelScope.launch {
            when (e) {

            }
        }
    }
}