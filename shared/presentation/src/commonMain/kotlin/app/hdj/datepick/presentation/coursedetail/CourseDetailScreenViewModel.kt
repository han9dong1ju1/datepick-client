package app.hdj.datepick.presentation.coursedetail

import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.model.course.Course
import app.hdj.datepick.domain.usecase.course.GetCourseDetailUseCase
import app.hdj.datepick.domain.usecase.place.GetPlacesByCourseIdUseCase
import app.hdj.datepick.presentation.PlatformViewModel
import app.hdj.datepick.presentation.UnidirectionalViewModelDelegate
import app.hdj.datepick.presentation.coursedetail.CourseDetailScreenViewModelDelegate.*
import app.hdj.datepick.utils.di.HiltViewModel
import app.hdj.datepick.utils.di.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

interface CourseDetailScreenViewModelDelegate : UnidirectionalViewModelDelegate<State, Effect, Event> {

    data class State(
        val courseState: LoadState<Course> = LoadState.loading(),
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
    }

}

@HiltViewModel
class CourseDetailScreenViewModel @Inject constructor(
    private val getPlacesByCourseIdUseCase: GetPlacesByCourseIdUseCase,
    private val getCourseDetailUseCase: GetCourseDetailUseCase,
) : PlatformViewModel(), CourseDetailScreenViewModelDelegate {

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)
    override val effect: Flow<Effect> = effectChannel.receiveAsFlow()

    private val courseDetail = MutableStateFlow<LoadState<Course>>(LoadState.loading())

    private val isBottomNavigationShown = MutableStateFlow(true)
    private val inEditMode = MutableStateFlow(false)

    override val state: StateFlow<State> =
        combine(courseDetail, inEditMode, isBottomNavigationShown) { courseState, inEditMode, isBottomNavigationShown ->
            State(
                courseState = courseState,
                inEditMode = inEditMode,
                isBottomNavigationShown = isBottomNavigationShown
            )
        }.asStateFlow(State(), platformViewModelScope)

    private fun loadCourse(courseId: Long) {
        platformViewModelScope.launch {
            getCourseDetailUseCase(courseId)
                .collect { courseDetail.emit(it) }

            getPlacesByCourseIdUseCase(courseId)
                .collect {

                }
        }
    }

    private fun setCourse(course: Course) {
        courseDetail.tryEmit(LoadState.success(course))
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
        }
    }

}