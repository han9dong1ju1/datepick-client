package app.hdj.datepick.android.ui.components.screens.others.create_course

import androidx.lifecycle.ViewModel
import app.hdj.datepick.android.ui.components.screens.others.create_course.CreateCourseViewModelDelegate.*
import app.hdj.datepick.ui.utils.ViewModelDelegate
import app.hdj.shared.client.data.datastore.AppDataStore
import app.hdj.shared.client.domain.entity.Course
import app.hdj.shared.client.domain.entity.Place
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
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
        val places: List<Place> = emptyList()
    )

    sealed class Effect {

    }

    sealed class Event {
        data class LoadDraftCourse(val courseId: String?) : Event()
        object ReloadContents : Event()
    }

}

@HiltViewModel
class CreateCourseViewModel @Inject constructor(

) : ViewModel(), CreateCourseViewModelDelegate {

    override val state: StateFlow<State>
        get() = TODO("Not yet implemented")

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)

    override val effect = effectChannel.receiveAsFlow()

    override fun event(event: Event) {

    }

}