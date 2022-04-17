package app.hdj.datepick.presentation.courselist

import app.hdj.datepick.domain.model.course.Course
import app.hdj.datepick.domain.usecase.course.SearchCoursesUseCase
import app.hdj.datepick.domain.usecase.course.params.CourseQueryParams
import app.hdj.datepick.presentation.PlatformViewModel
import app.hdj.datepick.presentation.UnidirectionalViewModelDelegate
import app.hdj.datepick.presentation.courselist.CourseListScreenViewModelDelegate.*
import app.hdj.datepick.utils.di.HiltViewModel
import app.hdj.datepick.utils.di.Inject
import com.kuuurt.paging.multiplatform.PagingData
import com.kuuurt.paging.multiplatform.helpers.cachedIn
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

interface CourseListScreenViewModelDelegate : UnidirectionalViewModelDelegate<State, Effect, Event> {

    data class State(
        val courses: Flow<PagingData<Course>> = flowOf(),
    )

    sealed interface Effect {

    }

    sealed interface Event {
        data class SearchCourses(val params: CourseQueryParams) : Event
        object Refresh : Event
    }

}

@HiltViewModel
class CourseListScreenViewModel @Inject constructor(
    private val searchCoursesUseCase: SearchCoursesUseCase
) : PlatformViewModel(), CourseListScreenViewModelDelegate {

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)
    override val effect: Flow<Effect> = effectChannel.receiveAsFlow()

    private val courseQueryParams = MutableStateFlow<CourseQueryParams?>(null)

    private val courses = courseQueryParams
        .filterNotNull()
        .map { params -> searchCoursesUseCase(params).pagingData.cachedIn(platformViewModelScope) }

    override val state: StateFlow<State> = combine(courses, flowOf(false)) { courses, _ ->
        State(courses)
    }.asStateFlow(State(), platformViewModelScope)

    private fun load(params: CourseQueryParams?) {
        platformViewModelScope.launch {
            courseQueryParams.emit(null)
            courseQueryParams.emit(params)
        }
    }

    override fun event(e: Event) {
        when (e) {
            is Event.SearchCourses -> load(e.params)
            Event.Refresh -> load(courseQueryParams.value)
        }
    }
}