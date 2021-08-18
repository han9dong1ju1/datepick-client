package app.hdj.datepick.android.ui.screens.others.course

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.hdj.datepick.android.ui.screens.others.course.CourseViewModelDelegate.*
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
    )

    sealed class Effect {
        data class ShowToastMessage(val message: String) : Effect()
    }

    sealed class Event {
    }

}

@HiltViewModel
class CourseViewModel @Inject constructor(
) : ViewModel(), CourseViewModelDelegate {

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)

    override val effect = effectChannel.receiveAsFlow()

    private val refreshState = MutableStateFlow(false)

    private var _courseId : String? = null
    private val courseId : String get() = requireNotNull(_courseId)

    override val state = TODO()

    override fun event(event: Event) {
        viewModelScope.launch {
            runCatching {

            }.onFailure {
                effectChannel.send(Effect.ShowToastMessage(""))
            }
        }
    }

}