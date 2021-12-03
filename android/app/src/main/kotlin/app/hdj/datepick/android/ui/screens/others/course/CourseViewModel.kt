package app.hdj.datepick.android.ui.screens.others.course

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.hdj.datepick.android.ui.screens.others.course.CourseViewModelDelegate.*
import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.LoadState.Companion.loading
import app.hdj.datepick.domain.model.course.Course
import app.hdj.datepick.ui.utils.ViewModelDelegate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@Composable
fun fakeCourseViewModel() = object : CourseViewModelDelegate {

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)

    override val state = MutableStateFlow(State())

    override val effect = effectChannel.receiveAsFlow()

    override fun event(event: Event) {

    }

}

interface CourseViewModelDelegate : ViewModelDelegate<State, Effect, Event> {

    class State(
        val course : LoadState<Course> = loading(),
        val isEditMode : Boolean = false
    )

    sealed class Effect {
        data class ShowToastMessage(val message: String) : Effect()
    }

    sealed class Event {
        class LoadCourse(val courseId: Long) : Event()
        object ToggleEditMode : Event()
    }

}

@HiltViewModel
class CourseViewModel @Inject constructor(
    
) : ViewModel(), CourseViewModelDelegate {

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)

    override val effect = effectChannel.receiveAsFlow()

    private val courseId = MutableStateFlow<Long?>(null)
    private val isEditMode = MutableStateFlow(false)

    override val state: StateFlow<State> = combine(isEditMode, flowOf(false)) { isEditMode, _ ->
        State(isEditMode = isEditMode)
    }.stateIn(viewModelScope, SharingStarted.Lazily, State())

    override fun event(event: Event) {
        viewModelScope.launch {
            runCatching {
                when (event) {
                    is Event.LoadCourse -> {
                        courseId.emit(event.courseId)
                    }
                    Event.ToggleEditMode -> {
                        isEditMode.emit(!isEditMode.value)
                    }
                }
            }.onFailure {
                effectChannel.send(Effect.ShowToastMessage(""))
            }
        }
    }

}