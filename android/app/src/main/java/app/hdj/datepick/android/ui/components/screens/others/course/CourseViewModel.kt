package app.hdj.datepick.android.ui.components.screens.others.course

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.hdj.datepick.android.ui.components.screens.others.course.CourseViewModelDelegate.*
import app.hdj.datepick.ui.utils.ViewModelDelegate
import app.hdj.shared.client.domain.StateData
import app.hdj.shared.client.domain.entity.CourseMetadata
import app.hdj.shared.client.domain.entity.CourseDetail
import app.hdj.shared.client.domain.entity.Place
import app.hdj.shared.client.domain.entity.PlaceWithMemo
import app.hdj.shared.client.domain.repo.CourseRepository
import app.hdj.shared.client.domain.repo.PlaceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@Composable
fun fakeCourseViewModel() = object : CourseViewModelDelegate {

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)

    override val state = MutableStateFlow(State())

    override val effect = effectChannel.receiveAsFlow()

    override fun event(event: Event) {

    }

}

interface CourseViewModelDelegate : ViewModelDelegate<State, Effect, Event> {

    data class State(
        val courseMetadata: StateData<CourseMetadata> = StateData.Loading(),
        val places: StateData<List<PlaceWithMemo>> = StateData.Loading(),
        val isRefreshing: Boolean = false,
    )

    sealed class Effect {
        data class ShowToastMessage(val message: String) : Effect()
    }

    sealed class Event {
        object ReloadContents : Event()
        data class NavigatedWithCourse(val courseMetadata: CourseMetadata) : Event()
        data class NavigatedWithCourseDetail(val courseDetail: CourseDetail) : Event()
        data class NavigatedWithCourseId(val courseId: String) : Event()
    }

}

@HiltViewModel
class CourseViewModel @Inject constructor(
    private val courseRepository: CourseRepository,
    private val placeRepository: PlaceRepository
) : ViewModel(), CourseViewModelDelegate {

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)

    override val effect = effectChannel.receiveAsFlow()

    private val refreshState = MutableStateFlow(false)

    private var _courseId : String? = null
    private val courseId : String get() = requireNotNull(_courseId)

    private val courseMetadata = MutableStateFlow<StateData<CourseMetadata>>(StateData.Loading())

    private val placesWithMemo =
        MutableStateFlow<StateData<List<PlaceWithMemo>>>(StateData.Loading())

    override val state = combine(
        courseMetadata, placesWithMemo, refreshState
    ) { metadata, placesWithMemo, refreshState ->
        State(metadata, placesWithMemo, refreshState)
    }.stateIn(viewModelScope, SharingStarted.Lazily, State())

    override fun event(event: Event) {
        viewModelScope.launch {
            runCatching {
                when (event) {
                    Event.ReloadContents -> refreshContents()
                    is Event.NavigatedWithCourse -> {
                        val metadata = event.courseMetadata
                        _courseId = metadata.id
                        courseMetadata.emit(StateData.Success(metadata))
                        courseRepository.getCourseDetail(metadata.id).collect { state ->
                            placesWithMemo.emit(state.mapBy(CourseDetail::placesWithMemo))
                        }
                    }
                    is Event.NavigatedWithCourseDetail -> {
                        val detail = event.courseDetail
                        _courseId = detail.id
                        courseMetadata.emit(StateData.Success(detail.metadata))
                        placesWithMemo.emit(StateData.Success(detail.placesWithMemo))
                    }
                    is Event.NavigatedWithCourseId -> {
                        _courseId = event.courseId
                        courseRepository.getCourseDetail(event.courseId).collect { state ->
                            courseMetadata.emit(state.mapBy(CourseDetail::metadata))
                            placesWithMemo.emit(state.mapBy(CourseDetail::placesWithMemo))
                        }
                    }
                }
            }.onFailure {
                effectChannel.send(Effect.ShowToastMessage(""))
            }
        }
    }

    private fun refreshContents() {
        viewModelScope.launch {

            refreshState.emit(true)

            when (courseMetadata.value) {
                is StateData.Loading -> refreshState.emit(false)
                is StateData.Success, is StateData.Failed -> {
                    courseRepository.getCourseDetail(courseId).collect {
                        courseMetadata.emit(it.mapBy(CourseDetail::metadata))
                        placesWithMemo.emit(it.mapBy(CourseDetail::placesWithMemo))
                        refreshState.emit(false)
                    }
                }
            }

        }
    }

}