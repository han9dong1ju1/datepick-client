package app.hdj.datepick.ui.screens.main.pick

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.hdj.datepick.ui.screens.main.home.HomeViewModelDelegate
import app.hdj.datepick.ui.screens.main.pick.PickViewModelDelegate.*
import app.hdj.datepick.ui.utils.ViewModelDelegate
import app.hdj.shared.client.domain.entity.Course
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


fun fakePickViewModel() = object : PickViewModelDelegate {

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)

    override val state = MutableStateFlow(State(emptyList()))

    override val effect = effectChannel.receiveAsFlow()

    override fun event(event: Event) {

    }

}

interface PickViewModelDelegate : ViewModelDelegate<State, Effect, Event> {

    data class State(
        val courses: List<Course> = emptyList(),
    )

    sealed class Effect {

    }

    sealed class Event {
        object ReloadContents : Event()
    }

}

@HiltViewModel
class PickViewModel @Inject constructor(

) : ViewModel(), PickViewModelDelegate {

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)
    override val effect = effectChannel.receiveAsFlow()

    override val state: StateFlow<State> = MutableStateFlow(State())

    override fun event(event: Event) {
        viewModelScope.launch {

        }
    }

}