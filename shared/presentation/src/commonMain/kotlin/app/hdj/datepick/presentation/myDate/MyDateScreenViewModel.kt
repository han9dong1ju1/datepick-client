package app.hdj.datepick.presentation.myDate

import app.hdj.datepick.domain.model.course.Course
import app.hdj.datepick.domain.usecase.course.GetMyDateCoursesUseCase
import app.hdj.datepick.domain.usecase.course.params.CourseQueryParams
import app.hdj.datepick.presentation.PlatformViewModel
import app.hdj.datepick.presentation.UnidirectionalViewModelDelegate
import app.hdj.datepick.presentation.myDate.MyDateScreenViewModelDelegate.*
import app.hdj.datepick.utils.di.HiltViewModel
import app.hdj.datepick.utils.di.Inject
import com.kuuurt.paging.multiplatform.PagingData
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

//    override val state: StateFlow<State> = queryParams
//        .map { State(getMyDateCoursesUseCase(it)) }
//        .asStateFlow(State(), platformViewModelScope)

    override val state: StateFlow<State> = MutableStateFlow(State(getMyDateCoursesUseCase(CourseQueryParams())))

    init {
        query(CourseQueryParams())
    }

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