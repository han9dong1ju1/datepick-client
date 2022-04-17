package app.hdj.datepick.presentation.featured

import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.isStateLoading
import app.hdj.datepick.domain.model.course.Course
import app.hdj.datepick.domain.model.featured.Featured
import app.hdj.datepick.domain.usecase.course.GetFeaturedCoursesUseCase
import app.hdj.datepick.domain.usecase.course.GetFirstPageCoursesUseCase
import app.hdj.datepick.domain.usecase.course.SearchCoursesUseCase
import app.hdj.datepick.domain.usecase.course.params.courseQueryParams
import app.hdj.datepick.domain.usecase.course.params.filterParams
import app.hdj.datepick.domain.usecase.course.params.pagingParams
import app.hdj.datepick.domain.usecase.featured.GetFeaturedDetailUseCase
import app.hdj.datepick.domain.usecase.featured.GetFeaturedDynamicLinkUseCase
import app.hdj.datepick.presentation.PlatformViewModel
import app.hdj.datepick.presentation.UnidirectionalViewModelDelegate
import app.hdj.datepick.presentation.featured.FeaturedDetailScreenViewModelDelegate.*
import app.hdj.datepick.utils.di.HiltViewModel
import app.hdj.datepick.utils.di.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


interface FeaturedDetailScreenViewModelDelegate : UnidirectionalViewModelDelegate<State, Effect, Event> {

    class State(
        val isContentRefreshing: Boolean = true,

        val featured: Featured? = null,
        val courses: List<Course> = emptyList()
    )

    sealed interface Effect {
        class ShowShareMenu(val shareUrl: String) : Effect
    }

    sealed interface Event {
        object Refresh : Event

        class LoadWithFeaturedId(val featuredId: Long) : Event
        class LoadWithFeatured(val featured: Featured) : Event

        object OpenShareMenu : Event
    }

}

@HiltViewModel
class FeaturedDetailScreenViewModel @Inject constructor(
    private val getFeaturedDetailUseCase: GetFeaturedDetailUseCase,
    private val getFirstPageCoursesUseCase: GetFirstPageCoursesUseCase,
    private val getFeaturedDynamicLinkUseCase: GetFeaturedDynamicLinkUseCase
) : FeaturedDetailScreenViewModelDelegate, PlatformViewModel() {

    private val effectChannel = Channel<Effect>(Channel.BUFFERED)
    override val effect: Flow<Effect> = effectChannel.receiveAsFlow()

    private val featured = MutableStateFlow<LoadState<Featured>>(LoadState.loading())
    private val featuredCourses = MutableStateFlow<LoadState<List<Course>>>(LoadState.loading())

    override val state = combine(
        featured,
        featuredCourses
    ) { featured, featuredCourses ->
        State(
            isContentRefreshing = featured.isStateLoading() || featuredCourses.isStateLoading(),
            featured = featured.getDataOrNull(),
            courses = featuredCourses.getDataOrNull() ?: emptyList()
        )
    }.asStateFlow(State(), platformViewModelScope)

    private fun loadFeatured(id: Long) {
        getFeaturedDetailUseCase.invoke(id)
            .onEach { featured.value = it }
            .catch { featured.value = LoadState.failed(it) }
            .launchIn(platformViewModelScope)
    }

    private fun loadFeaturedCourses(id: Long) {
        getFirstPageCoursesUseCase(courseQueryParams { filterParams { featuredId = id } })
            .onEach { featuredCourses.value = it }
            .catch { featuredCourses.value = LoadState.failed(it) }
            .launchIn(platformViewModelScope)
    }

    override fun event(e: Event) {
        when (e) {
            is Event.LoadWithFeaturedId -> {
                loadFeatured(e.featuredId)
                loadFeaturedCourses(e.featuredId)
            }
            is Event.LoadWithFeatured -> {
                featured.tryEmit(LoadState.success(e.featured))
                loadFeaturedCourses(e.featured.id)
            }
            Event.OpenShareMenu -> {
                platformViewModelScope.launch {
                    val featured = featured.first().getDataOrNull() ?: return@launch
                    effectChannel.send(
                        Effect.ShowShareMenu(
                            getFeaturedDynamicLinkUseCase.invoke(featured).first()
                        )
                    )
                }
            }
            Event.Refresh -> {
                val id = featured.value.getDataOrNull()?.id ?: return
                loadFeatured(id)
                loadFeaturedCourses(id)
            }
        }
    }
}