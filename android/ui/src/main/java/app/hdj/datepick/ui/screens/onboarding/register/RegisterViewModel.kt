package app.hdj.datepick.ui.screens.onboarding.register

import androidx.lifecycle.ViewModel
import app.hdj.datepick.ui.screens.onboarding.register.RegisterViewModelDelegate.*
import app.hdj.datepick.ui.utils.ViewModelDelegate
import app.hdj.shared.client.domain.entity.Course
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject


fun fakeRegisterViewModel() = object : RegisterViewModelDelegate {

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)

    override val state = MutableStateFlow(State(emptyList()))

    override val effect = effectChannel.receiveAsFlow()

    override fun event(event: Event) {

    }

}

interface RegisterViewModelDelegate : ViewModelDelegate<State, Effect, Event> {

    data class State(
        val courses: List<Course>,
    )

    sealed class Effect {

    }

    sealed class Event {
        object ReloadContents : Event()
    }

}

@HiltViewModel
class RegisterViewModel @Inject constructor(

) : ViewModel(), RegisterViewModelDelegate {

    override val state: StateFlow<State>
        get() = TODO("Not yet implemented")

    override val effect: Flow<Effect>
        get() = TODO("Not yet implemented")

    override fun event(event: Event) {
        TODO("Not yet implemented")
    }

}