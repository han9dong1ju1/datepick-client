package app.hdj.datepick.ui.screens.main.profile

import app.hdj.datepick.ui.StateViewModel
import app.hdj.shared.client.domain.entity.Course
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(

) : StateViewModel<ProfileViewModel.State, ProfileViewModel.Effect, ProfileViewModel.Event>() {

    class State

    sealed class Effect {

    }

    sealed class Event {

    }

    override val state: StateFlow<State>
        get() = TODO("Not yet implemented")

    override val effect: StateFlow<Effect>
        get() = TODO("Not yet implemented")

    override fun event(event: Event) {

    }

}