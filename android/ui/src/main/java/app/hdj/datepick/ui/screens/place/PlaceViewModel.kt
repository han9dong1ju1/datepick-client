package app.hdj.datepick.ui.screens.place

import app.hdj.datepick.ui.StateViewModel
import app.hdj.shared.client.domain.entity.Course
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class PlaceViewModel @Inject constructor(

) : StateViewModel<PlaceViewModel.State, PlaceViewModel.Effect, PlaceViewModel.Event>() {

    data class State(
        val courses : List<Course>,
    )

    sealed class Effect {

    }

    sealed class Event {
        object ReloadContents : Event()
    }

    override val state: StateFlow<State>
        get() = TODO("Not yet implemented")

    override val effect: StateFlow<Effect>
        get() = TODO("Not yet implemented")

    override fun event(event: Event) {

    }

}