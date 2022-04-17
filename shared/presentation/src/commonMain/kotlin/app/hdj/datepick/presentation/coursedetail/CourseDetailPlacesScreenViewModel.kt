package app.hdj.datepick.presentation.coursedetail

import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.model.place.Place
import app.hdj.datepick.domain.usecase.place.GetPlacesByCourseIdUseCase
import app.hdj.datepick.presentation.PlatformViewModel
import app.hdj.datepick.presentation.UnidirectionalViewModelDelegate
import app.hdj.datepick.presentation.coursedetail.CourseDetailPlacesScreenViewModelDelegate.*
import app.hdj.datepick.utils.di.HiltViewModel
import app.hdj.datepick.utils.di.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*

interface CourseDetailPlacesScreenViewModelDelegate : UnidirectionalViewModelDelegate<State, Effect, Event> {

    data class State(
        val placesState: LoadState<List<Place>> = LoadState.idle()
    )

    sealed interface Effect {
    }

    sealed interface Event {
        data class SetCourseId(val courseId: Long) : Event
    }

}

@HiltViewModel
class CourseDetailPlacesScreenViewModel @Inject constructor(
    private val getPlacesByCourseIdUseCase: GetPlacesByCourseIdUseCase,
) : PlatformViewModel(), CourseDetailPlacesScreenViewModelDelegate {

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)
    override val effect: Flow<Effect> = effectChannel.receiveAsFlow()

    private val courseId = MutableStateFlow<Long?>(null)

    private val places = courseId
        .filterNotNull()
        .flatMapConcat { getPlacesByCourseIdUseCase(it) }

    override val state: StateFlow<State> = places.map {
        State(it)
    }.asStateFlow(State(), platformViewModelScope)

    override fun event(e: Event) {
        when (e) {
            is Event.SetCourseId -> courseId.tryEmit(e.courseId)
        }
    }

}