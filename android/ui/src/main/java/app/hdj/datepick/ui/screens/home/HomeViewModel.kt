package app.hdj.datepick.ui.screens.home

import app.hdj.datepick.ui.StateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

) : StateViewModel<HomeViewModel.State, HomeViewModel.Effect, HomeViewModel.Event>() {

    class State()

    class Effect()

    sealed class Event {

    }

    override val state: StateFlow<State>
        get() = TODO("Not yet implemented")

    override val effect: StateFlow<Effect>
        get() = TODO("Not yet implemented")

    override fun event(event: Event) {

    }

}