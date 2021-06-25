package app.hdj.datepick.ui.screens.others.course

import androidx.compose.runtime.Composable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.hdj.datepick.ui.screens.others.course.CourseViewModelDelegate.*
import app.hdj.datepick.ui.utils.ViewModelDelegate
import app.hdj.shared.client.domain.entity.Course
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@Composable
fun fakeCourseViewModel() = object : CourseViewModelDelegate {

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)

    override val state = MutableStateFlow(State(emptyList()))

    override val effect = effectChannel.receiveAsFlow()

    override fun event(event: Event) {

    }

}

interface CourseViewModelDelegate :
    ViewModelDelegate<State, Effect, Event> {

    data class State(
        val courses: List<Course>,
    )

    sealed class Effect {
        data class ShowToast(val message: String) : Effect()
    }

    sealed class Event {
        object ReloadContents : Event()
    }

}

@HiltViewModel
class CourseViewModel @Inject constructor(

) : ViewModel(), CourseViewModelDelegate {

    init {
        refreshContents()
    }

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)

    override val effect = effectChannel.receiveAsFlow()

    override val state: StateFlow<State>
        get() = TODO("Not yet implemented")

    override fun event(event: Event) {
        viewModelScope.launch {
            event.runCatching {
                when (this) {
                    Event.ReloadContents -> {

                    }
                }
            }.onFailure {
                effectChannel.send(Effect.ShowToast(""))
            }
        }
    }

    private fun refreshContents() {
        viewModelScope.launch {

        }
    }

}