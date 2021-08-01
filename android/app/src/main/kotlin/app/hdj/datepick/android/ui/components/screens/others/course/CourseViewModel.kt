package app.hdj.datepick.android.ui.components.screens.others.course

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.hdj.datepick.android.ui.components.screens.others.course.CourseViewModelDelegate.*
import app.hdj.datepick.ui.utils.ViewModelDelegate
import app.hdj.datepick.domain.StateData
import app.hdj.datepick.data.model.course.CourseMetadata
import app.hdj.datepick.data.model.course.CourseDetail
import app.hdj.datepick.data.model.course.PlaceWithMemo
import app.hdj.datepick.domain.repo.CourseRepository
import app.hdj.datepick.domain.repo.PlaceRepository
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
        val courseMetadata: app.hdj.datepick.domain.StateData<app.hdj.datepick.data.model.course.CourseMetadata> = app.hdj.datepick.domain.StateData.Loading(),
        val places: app.hdj.datepick.domain.StateData<List<app.hdj.datepick.data.model.course.PlaceWithMemo>> = app.hdj.datepick.domain.StateData.Loading(),
        val isRefreshing: Boolean = false,
    )

    sealed class Effect {
        data class ShowToastMessage(val message: String) : Effect()
    }

    sealed class Event {
        object ReloadContents : Event()
        data class NavigatedWithCourse(val courseMetadata: app.hdj.datepick.data.model.course.CourseMetadata) : Event()
        data class NavigatedWithCourseDetail(val courseDetail: app.hdj.datepick.data.model.course.CourseDetail) : Event()
        data class NavigatedWithCourseId(val courseId: String) : Event()
    }

}

@HiltViewModel
class CourseViewModel @Inject constructor(
    private val courseRepository: app.hdj.datepick.domain.repo.CourseRepository,
    private val placeRepository: app.hdj.datepick.domain.repo.PlaceRepository
) : ViewModel(), CourseViewModelDelegate {

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)

    override val effect = effectChannel.receiveAsFlow()

    private val refreshState = MutableStateFlow(false)

    private var _courseId : String? = null
    private val courseId : String get() = requireNotNull(_courseId)

    private val courseMetadata = MutableStateFlow<app.hdj.datepick.domain.StateData<app.hdj.datepick.data.model.course.CourseMetadata>>(
        app.hdj.datepick.domain.StateData.Loading())

    private val placesWithMemo =
        MutableStateFlow<app.hdj.datepick.domain.StateData<List<app.hdj.datepick.data.model.course.PlaceWithMemo>>>(app.hdj.datepick.domain.StateData.Loading())

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
                        courseMetadata.emit(app.hdj.datepick.domain.StateData.Success(metadata))
                        courseRepository.getCourseDetail(metadata.id).collect { state ->
                            placesWithMemo.emit(state.mapBy(app.hdj.datepick.data.model.course.CourseDetail::placesWithMemo))
                        }
                    }
                    is Event.NavigatedWithCourseDetail -> {
                        val detail = event.courseDetail
                        _courseId = detail.id
                        courseMetadata.emit(app.hdj.datepick.domain.StateData.Success(detail.metadata))
                        placesWithMemo.emit(app.hdj.datepick.domain.StateData.Success(detail.placesWithMemo))
                    }
                    is Event.NavigatedWithCourseId -> {
                        _courseId = event.courseId
                        courseRepository.getCourseDetail(event.courseId).collect { state ->
                            courseMetadata.emit(state.mapBy(app.hdj.datepick.data.model.course.CourseDetail::metadata))
                            placesWithMemo.emit(state.mapBy(app.hdj.datepick.data.model.course.CourseDetail::placesWithMemo))
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
                is app.hdj.datepick.domain.StateData.Loading -> refreshState.emit(false)
                is app.hdj.datepick.domain.StateData.Success, is app.hdj.datepick.domain.StateData.Failed -> {
                    courseRepository.getCourseDetail(courseId).collect {
                        courseMetadata.emit(it.mapBy(app.hdj.datepick.data.model.course.CourseDetail::metadata))
                        placesWithMemo.emit(it.mapBy(app.hdj.datepick.data.model.course.CourseDetail::placesWithMemo))
                        refreshState.emit(false)
                    }
                }
            }

        }
    }

}