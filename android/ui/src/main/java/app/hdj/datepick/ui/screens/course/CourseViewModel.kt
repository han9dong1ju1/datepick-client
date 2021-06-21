package app.hdj.datepick.ui.screens.course

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.hdj.datepick.ui.screens.course.CourseViewModelDelegate.*
import app.hdj.datepick.ui.screens.login.LoginViewModelDelegate
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
        data class RequestContents(val courseId: Long) : Event()
    }

}

@HiltViewModel
class CourseViewModel @Inject constructor(

) : ViewModel(), CourseViewModelDelegate {

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
                    is Event.RequestContents -> {
                        courseId
                    }
                }
            }.onFailure {
                effectChannel.send(Effect.ShowToast(""))
            }
        }
    }

}