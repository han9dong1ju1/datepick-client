package app.hdj.datepick.android.ui.components.dialog.login

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import app.hdj.datepick.android.ui.components.dialog.login.LoginViewModelDelegate.*
import app.hdj.datepick.ui.utils.ViewModelDelegate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
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
    )

    sealed class Effect {

    }

    sealed class Event {
        object ReloadContents : Event()
    }

}

@HiltViewModel
class LoginViewModel @Inject constructor(

) : ViewModel(), LoginViewModelDelegate {

    override val state: StateFlow<State> = MutableStateFlow(State())

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)

    override val effect = effectChannel.receiveAsFlow()

    override fun event(event: Event) {
    }

}