package app.hdj.datepick.android.ui.screens.others.featuredDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.hdj.datepick.android.ui.screens.AppNavigationGraph
import app.hdj.datepick.android.ui.screens.AppNavigationGraph.FeaturedDetail.graphWithArgument
import app.hdj.datepick.android.ui.screens.others.featuredDetail.FeaturedDetailViewModelDelegate.*
import app.hdj.datepick.android.utils.createDynamicLink
import app.hdj.datepick.domain.EmptyLoadState
import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.LoadState.Companion.failed
import app.hdj.datepick.domain.LoadState.Companion.loading
import app.hdj.datepick.domain.LoadState.Companion.success
import app.hdj.datepick.domain.isStateSucceed
import app.hdj.datepick.domain.map
import app.hdj.datepick.domain.model.course.Course
import app.hdj.datepick.domain.model.course.FeatureCourseMatas
import app.hdj.datepick.domain.model.featured.Featured
import app.hdj.datepick.domain.model.featured.FeaturedWithContent
import app.hdj.datepick.domain.usecase.featured.GetFeaturedUseCase
import app.hdj.datepick.ui.utils.ViewModelDelegate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


fun fakeFeaturedDetailViewModel() = object : FeaturedDetailViewModelDelegate {

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)

    override val state = MutableStateFlow(State())

    override val effect = effectChannel.receiveAsFlow()

    override fun event(event: Event) {

    }

}

interface FeaturedDetailViewModelDelegate : ViewModelDelegate<State, Effect, Event> {

    data class State(
        val featured: LoadState<Featured> = loading(),
        val content: LoadState<String> = loading(),
        val courses: LoadState<List<Course>> = loading(),
        val shareUrlLoadState: EmptyLoadState? = null
    )

    sealed class Effect {
        class OpenShareMenu(val link: String) : Effect()
    }

    sealed class Event {
        object Retry : Event()
        class FetchFeaturedById(val featuredId: Long) : Event()
        class ServePreviousFeaturedData(val featured: Featured) : Event()
        object CreateShareUrl : Event()
    }

}

@HiltViewModel
@OptIn(FlowPreview::class)
class FeaturedDetailViewModel @Inject constructor(
    getFeaturedUseCase: GetFeaturedUseCase
) : ViewModel(), FeaturedDetailViewModelDelegate {

    private val previousFeaturedData = MutableStateFlow<Featured?>(null)
    private val featuredId = MutableStateFlow<Long?>(null)

    private val shareUrlLoadState = MutableStateFlow<EmptyLoadState?>(null)

    private val featuredDetail = featuredId
        .flatMapConcat {
            if (it != null) getFeaturedUseCase.execute(it)
            else flowOf(loading())
        }

    override val state = combine(
        previousFeaturedData,
        featuredDetail,
        shareUrlLoadState
    ) { previousFeaturedData, featuredDetail, shareUrlLoadState ->
        State(
            if (featuredDetail.isStateSucceed() && previousFeaturedData == null) featuredDetail.map { it.featured }
            else if (previousFeaturedData != null) success(previousFeaturedData)
            else loading(),
            featuredDetail.map { it.featured.content },
            featuredDetail.map { it.courses.map { it.courseMeta } },
            shareUrlLoadState
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        State()
    )

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)

    override val effect = effectChannel.receiveAsFlow()

    override fun event(event: Event) {
        viewModelScope.launch {
            when (event) {
                is Event.ServePreviousFeaturedData -> {
                    previousFeaturedData.emit(event.featured)
                    featuredId.emit(event.featured.id)
                }
                is Event.FetchFeaturedById -> {
                    featuredId.emit(event.featuredId)
                }
                Event.Retry -> {
                    featuredId.emit(featuredId.value)
                }
                Event.CreateShareUrl -> {

                    val featured = state.value.featured as LoadState.Success

                    shareUrlLoadState.emit(loading())

                    val url = createDynamicLink(
                        graphWithArgument(featured = featured.data).route,
                        featured.data.title,
                        featured.data.description,
                        featured.data.photoUrl,
                        1000,
                        "1000"
                    )

                    if (url != null) {
                        shareUrlLoadState.emit(success(Unit))
                        effectChannel.send(Effect.OpenShareMenu(url.toString()))
                    } else {
                        shareUrlLoadState.emit(failed(Exception("Can't generate url")))
                    }
                }

            }
        }
    }

}