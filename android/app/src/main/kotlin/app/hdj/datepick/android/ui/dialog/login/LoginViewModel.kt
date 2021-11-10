package app.hdj.datepick.android.ui.dialog.login

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.hdj.datepick.android.ui.dialog.login.LoginViewModelDelegate.*
import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.usecase.user.AuthenticateMeUseCase
import app.hdj.datepick.ui.utils.ViewModelDelegate
import app.hdj.datepick.utils.PlatformLogger
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.gitlive.firebase.auth.AuthCredential
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@Composable
fun fakeLoginViewModel() = object : LoginViewModelDelegate {

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)

    override val state = MutableStateFlow(State())

    override val effect = effectChannel.receiveAsFlow()

    override fun event(event: Event) {

    }

}

interface LoginViewModelDelegate :
    ViewModelDelegate<State, Effect, Event> {

    class State(
        val loginState: LoadState<Unit>? = null
    )

    sealed class Effect {
        object ShowRegisterPage : Effect()
        object DismissDialog : Effect()
    }

    sealed class Event {
        class RequestSignIn(val credential: AuthCredential) : Event()
    }

}

@HiltViewModel
@OptIn(FlowPreview::class)
class LoginViewModel @Inject constructor(
    private val authenticateMeUseCase: AuthenticateMeUseCase,
) : ViewModel(), LoginViewModelDelegate {

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)
    override val effect = effectChannel.receiveAsFlow()

    private val authenticateTrigger = MutableStateFlow<AuthCredential?>(null)

    override val state: StateFlow<State> = authenticateTrigger
        .filterNotNull()
        .flatMapConcat { authenticateMeUseCase.execute(it) }
        .map {
            when (it) {
                is LoadState.Loading -> Unit
                is LoadState.Failed -> {
                    effectChannel.send(Effect.ShowRegisterPage)
                }
                is LoadState.Success -> effectChannel.send(Effect.DismissDialog)
            }
            State(it)
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, State())

    override fun event(event: Event) {
        viewModelScope.launch {
            when (event) {
                is Event.RequestSignIn -> {
                    authenticateTrigger.emit(event.credential)
                }
            }
        }
    }

}