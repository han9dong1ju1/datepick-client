package app.hdj.datepick.presentation.coursedetail

import app.hdj.datepick.android.utils.moved
import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.model.course.Course
import app.hdj.datepick.domain.model.place.Place
import app.hdj.datepick.domain.usecase.course.GetCourseDetailUseCase
import app.hdj.datepick.domain.usecase.place.GetPlacesByCourseIdUseCase
import app.hdj.datepick.presentation.PlatformViewModel
import app.hdj.datepick.presentation.UnidirectionalViewModelDelegate
import app.hdj.datepick.presentation.coursedetail.CourseDetailScreenViewModelDelegate.*
import app.hdj.datepick.presentation.utils.toLoadState
import app.hdj.datepick.utils.di.HiltViewModel
import app.hdj.datepick.utils.di.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

interface CourseDetailScreenViewModelDelegate : UnidirectionalViewModelDelegate<State, Effect, Event> {

    data class State(
        val courseState: LoadState<Course> = LoadState.idle(),
        val placesState: LoadState<List<Place>> = LoadState.idle(),
        val inEditMode: Boolean = false,
        val isBottomNavigationShown: Boolean = true
    )

    sealed interface Effect {
    }

    sealed interface Event {
        data class LoadCourse(val courseId: Long) : Event
        data class SetCourse(val course: Course) : Event

        data class DiaryScrolled(val currentIndex: Int) : Event
        data class SetInEditMode(val inEditMode: Boolean) : Event

        data class ReorderPlaces(val fromIdx: Int, val toIdx: Int) : Event
    }

}

@HiltViewModel
class CourseDetailScreenViewModel @Inject constructor(
    private val getPlacesByCourseIdUseCase: GetPlacesByCourseIdUseCase,
    private val getCourseDetailUseCase: GetCourseDetailUseCase,
) : PlatformViewModel(), CourseDetailScreenViewModelDelegate {

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)
    override val effect: Flow<Effect> = effectChannel.receiveAsFlow()

    private val courseState = MutableStateFlow<LoadState<Course>>(LoadState.idle())

    private val placesState = MutableStateFlow<LoadState<List<Place>>>(LoadState.idle())

    private val isBottomNavigationShown = MutableStateFlow(true)
    private val inEditMode = MutableStateFlow(false)

    override val state: StateFlow<State> =
        combine(
            courseState,
            placesState,
            inEditMode,
            isBottomNavigationShown
        ) { courseState, placesState, inEditMode, isBottomNavigationShown ->
            State(
                courseState = courseState,
                placesState = placesState,
                inEditMode = inEditMode,
                isBottomNavigationShown = isBottomNavigationShown
            )
        }.asStateFlow(State(), platformViewModelScope)

    private fun loadCourse(courseId: Long) {
        getCourseDetailUseCase(courseId)
            .toLoadState()
            .onEach { courseState.emit(it) }
            .launchIn(platformViewModelScope)

        getPlacesByCourseIdUseCase(courseId)
            .toLoadState()
            .onEach { placesState.emit(it) }
            .launchIn(platformViewModelScope)
    }

    private fun setCourse(course: Course) {
        courseState.tryEmit(LoadState.success(course))

        getPlacesByCourseIdUseCase(course.id)
            .toLoadState()
            .onEach { placesState.emit(it) }
            .launchIn(platformViewModelScope)
    }

    override fun event(e: Event) {
        when (e) {
            is Event.LoadCourse -> loadCourse(e.courseId)
            is Event.SetCourse -> setCourse(e.course)
            is Event.DiaryScrolled -> {
                platformViewModelScope.launch {
                    isBottomNavigationShown.emit(e.currentIndex == 0)
                }
            }
            is Event.SetInEditMode -> {
                platformViewModelScope.launch {
                    inEditMode.emit(e.inEditMode)
                }
            }
            is Event.ReorderPlaces -> {
                platformViewModelScope.launch {
                    val places = placesState.value.dataOrNull ?: return@launch
                    placesState.emit(LoadState.success(places.moved(e.fromIdx, e.toIdx)))
                }
            }
        }
    }

}