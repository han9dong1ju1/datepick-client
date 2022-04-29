package app.hdj.datepick.presentation.login

import app.hdj.datepick.domain.isStateSucceed
import app.hdj.datepick.domain.usecase.auth.RequestSignInUseCase
import app.hdj.datepick.domain.usecase.auth.params.SignInRequest
import app.hdj.datepick.domain.usecase.auth.params.SignInRequest.Provider.GOOGLE
import app.hdj.datepick.presentation.PlatformViewModel
import app.hdj.datepick.presentation.UnidirectionalViewModelDelegate
import app.hdj.datepick.presentation.login.LoginBottomSheetDialogViewModelDelegate.*
import app.hdj.datepick.presentation.utils.toLoadState
import app.hdj.datepick.utils.di.HiltViewModel
import app.hdj.datepick.utils.di.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*


interface LoginBottomSheetDialogViewModelDelegate :
    UnidirectionalViewModelDelegate<State, Effect, Event> {

    class State

    sealed interface Effect {
        object SignInSucceed : Effect
    }

    sealed interface Event {
        data class RequestGoogleLogin(val code: String) : Event
    }

}

@HiltViewModel
class LoginBottomSheetDialogViewModel @Inject constructor(
    private val requestSignInUseCase: RequestSignInUseCase
) : PlatformViewModel(), LoginBottomSheetDialogViewModelDelegate {

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)
    override val effect: Flow<Effect> = effectChannel.receiveAsFlow()

    override val state: StateFlow<State> = MutableStateFlow(State())

    override fun event(e: Event) {
        when (e) {
            is Event.RequestGoogleLogin -> {
                requestSignInUseCase(SignInRequest(e.code, GOOGLE))
                    .toLoadState()
                    .onEach {
                        if (it.isStateSucceed()) {
                            effectChannel.send(Effect.SignInSucceed)
                        }
                    }.launchIn(platformViewModelScope)
            }
        }
    }

}
