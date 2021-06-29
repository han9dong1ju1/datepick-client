package app.hdj.datepick.ui.components.screens.others.course

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.hdj.datepick.ui.components.screens.others.course.CourseViewModelDelegate.*
import app.hdj.datepick.ui.utils.ViewModelDelegate
import app.hdj.shared.client.domain.StateData
import app.hdj.shared.client.domain.entity.Course
import app.hdj.shared.client.domain.repo.CourseRepository
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

    override val state = MutableStateFlow(State())

    override val effect = effectChannel.receiveAsFlow()

    override fun event(event: Event) {

    }

}

interface CourseViewModelDelegate :
    ViewModelDelegate<State, Effect, Event> {

    data class State(
        val course: StateData<Course> = StateData.Loading(),
    )

    sealed class Effect {
        data class ShowToastMessage(val message: String) : Effect()
    }

    sealed class Event {
        object ReloadContents : Event()
    }

}

@HiltViewModel
class CourseViewModel @Inject constructor(
    private val courseRepository: CourseRepository
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
                    Event.ReloadContents -> refreshContents()
                }
            }.onFailure {
                effectChannel.send(Effect.ShowToastMessage(""))
            }
        }
    }

    private fun refreshContents() {
        viewModelScope.launch {

        }
    }

}