package app.hdj.datepick.presentation.createCourse

import app.hdj.datepick.domain.model.course.Course
import app.hdj.datepick.domain.model.course.CourseTheme
import app.hdj.datepick.presentation.PlatformViewModel
import app.hdj.datepick.presentation.UnidirectionalViewModelDelegate
import app.hdj.datepick.presentation.createCourse.CreateCourseScreenViewModelDelegate.*
import app.hdj.datepick.utils.HiltViewModel
import app.hdj.datepick.utils.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

interface CreateCourseScreenViewModelDelegate : UnidirectionalViewModelDelegate<State, Effect, Event> {

    class State(
        val themes: List<CourseTheme>? = null,
        val selectedThemes: List<CourseTheme>? = null,
    )

    sealed interface Effect {
        //        class CourseCreated(val course: Course) : Effect
        class CourseCreated : Effect
    }

    sealed interface Event {
        data class SelectTheme(val theme: CourseTheme) : Event
        data class CreateCourse(val name: String) : Event
    }

}

@HiltViewModel
class CreateCourseScreenViewModel @Inject constructor() : PlatformViewModel(), CreateCourseScreenViewModelDelegate {

    private val effectChannel = Channel<Effect>(Channel.BUFFERED)

    override val effect: Flow<Effect> = effectChannel.receiveAsFlow()

    private val themes = flow {
        delay(1000)
        emit(listOf("크리스마스", "기념일", "먹거리", "액티비티", "생일", "깜짝 이벤트", "선물", "축제", "여행").mapIndexed { index, s ->
            object : CourseTheme {
                override val id: Long get() = index.toLong()
                override val name: String = s
            }
        })
    }

    private val selectedThemes = MutableStateFlow(emptyList<CourseTheme>())

    override val state: StateFlow<State> = combine(
        themes,
        selectedThemes,
    ) { themes, selectedThemes ->
        State(themes, selectedThemes)
    }.asStateFlow(State(), platformViewModelScope)

    override fun event(e: Event) {
        platformViewModelScope.launch {
            when (e) {
                is Event.SelectTheme -> {
                    if (e.theme in selectedThemes.value) {
                        selectedThemes.emit(selectedThemes.value - e.theme)
                    } else {
                        selectedThemes.emit(selectedThemes.value + e.theme)
                    }
                }
                is Event.CreateCourse -> {
                    val name = e.name
                    val selectedThemes = selectedThemes.value
                    effectChannel.send(Effect.CourseCreated())
                }
            }
        }
    }
}