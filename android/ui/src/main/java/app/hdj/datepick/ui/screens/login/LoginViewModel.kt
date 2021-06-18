package app.hdj.datepick.ui.screens.login

import app.hdj.datepick.ui.StateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(

) : StateViewModel<LoginViewModel.State, LoginViewModel.Effect, LoginViewModel.Event>() {

    data class State(
        val showProgress: Boolean
    )

    sealed class Effect {
        object LoginSucceed : Effect()
    }

    sealed class Event {
        object RequestGoogleLogin : Event()
    }

    override val state: StateFlow<State>
        get() = TODO("Not yet implemented")

    override val effect: StateFlow<Effect>
        get() = TODO("Not yet implemented")

    override fun event(event: Event) {

    }

}
