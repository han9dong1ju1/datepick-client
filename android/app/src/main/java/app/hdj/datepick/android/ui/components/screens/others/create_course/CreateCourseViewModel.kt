package app.hdj.datepick.android.ui.components.screens.others.create_course

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.hdj.datepick.android.ui.components.screens.others.create_course.CreateCourseViewModelDelegate.*
import app.hdj.datepick.ui.utils.ViewModelDelegate
import app.hdj.shared.client.domain.StateData
import app.hdj.shared.client.domain.entity.CourseMetadata
import app.hdj.shared.client.domain.entity.Place
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


fun fakeCreateCourseViewModel() = object : CreateCourseViewModelDelegate {

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)

    override val state = MutableStateFlow(State())

    override val effect = effectChannel.receiveAsFlow()

    override fun event(event: Event) {

    }

}

interface CreateCourseViewModelDelegate : ViewModelDelegate<State, Effect, Event> {

    class State(
        val places: List<Place> = emptyList(),
        val uploadState : StateData<CourseMetadata>? = null
    )

    sealed class Effect {

    }

    sealed class Event {

        data class LoadDraftCourse(val courseId: String?) : Event()

        data class Upload(
            val name: String,
            val places: List<Place>,
        ) : Event()

        data class AddPlace(val place: Place) : Event()

        data class RemovePlace(val place: Place) : Event()

        object ReloadContents : Event()
    }

}

@HiltViewModel
class CreateCourseViewModel @Inject constructor(

) : ViewModel(), CreateCourseViewModelDelegate {

    private val places = MutableStateFlow(emptyList<Place>())

    override val state = combine(places) {
        State()
    }.stateIn(viewModelScope, SharingStarted.Lazily, State())

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)

    override val effect = effectChannel.receiveAsFlow()

    override fun event(event: Event) {
        viewModelScope.launch {
            when (event) {
                is Event.AddPlace -> places.emit(places.value + event.place)
                is Event.RemovePlace -> places.emit(places.value - event.place)
                is Event.Upload -> {

                }
                is Event.LoadDraftCourse -> {

                }
                Event.ReloadContents -> {

                }
            }
        }
    }

}