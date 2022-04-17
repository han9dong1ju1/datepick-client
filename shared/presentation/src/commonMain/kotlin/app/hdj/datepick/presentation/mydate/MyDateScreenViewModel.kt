package app.hdj.datepick.presentation.mydate

import app.hdj.datepick.domain.model.course.Course
import app.hdj.datepick.domain.usecase.course.GetMyDateCoursesUseCase
import app.hdj.datepick.domain.usecase.course.params.CourseQueryParams
import app.hdj.datepick.domain.usecase.course.params.courseQueryParams
import app.hdj.datepick.domain.usecase.course.params.filterParams
import app.hdj.datepick.presentation.PlatformViewModel
import app.hdj.datepick.presentation.UnidirectionalViewModelDelegate
import app.hdj.datepick.presentation.mydate.MyDateScreenViewModelDelegate.*
import app.hdj.datepick.utils.di.HiltViewModel
import app.hdj.datepick.utils.di.Inject
import com.kuuurt.paging.multiplatform.PagingData
import com.kuuurt.paging.multiplatform.helpers.cachedIn
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

interface MyDateScreenViewModelDelegate : UnidirectionalViewModelDelegate<State, Effect, Event> {

    class State(
        val myDateCourses: Flow<PagingData<Course>> = flowOf(),
    )

    class Effect()

    sealed interface Event {
        object Refresh : Event
        class Query(val queryParams: CourseQueryParams) : Event
    }
}

@HiltViewModel
class MyDateScreenViewModel @Inject constructor(
    private val getMyDateCoursesUseCase: GetMyDateCoursesUseCase
) : MyDateScreenViewModelDelegate, PlatformViewModel() {

    private val effectChannel = Channel<Effect>(Channel.BUFFERED)
    override val effect: Flow<Effect> = effectChannel.receiveAsFlow()

    private val queryParams = MutableStateFlow(CourseQueryParams())

    private val myDate = queryParams
        .filterNotNull()
        .flatMapConcat { getMyDateCoursesUseCase(it) }
        .map { it.pagingData.cachedIn(platformViewModelScope) }

    override val state: StateFlow<State> = myDate
        .map { State(it) }
        .asStateFlow(State(), platformViewModelScope)

    private fun query(params: CourseQueryParams) {
        platformViewModelScope.launch {
            queryParams.emit(params)
        }
    }

    override fun event(e: Event) {
        platformViewModelScope.launch {
            when (e) {
                is Event.Refresh -> query(queryParams.value.copy())
                is Event.Query -> query(e.queryParams)
            }
        }
    }
}