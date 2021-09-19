package app.hdj.datepick.android.ui.screens.main.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.hdj.datepick.android.ui.screens.main.map.MapViewModelDelegate.*
import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.LoadState.Companion.loading
import app.hdj.datepick.domain.LoadState.Companion.success
import app.hdj.datepick.domain.isStateLoading
import app.hdj.datepick.domain.model.course.Course
import app.hdj.datepick.ui.utils.ViewModelDelegate
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

fun fakeMapViewModel() = object : MapViewModelDelegate {

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)

    override val state = MutableStateFlow(State())

    override val effect = effectChannel.receiveAsFlow()

    override fun event(event: Event) {

    }

}

interface MapViewModelDelegate : ViewModelDelegate<State, Effect, Event> {

    data class State(
        val courses: LoadState<List<Course>> = loading(),
        val selectedCoursePlaces: LoadState<List<LatLng>> = loading()
    ) {
        val showProgressBar
            get() = selectedCoursePlaces.isStateLoading()
    }

    sealed class Effect {

    }

    sealed class Event {
        class LoadCoursesWithQuery(val query : String) : Event()
        class LoadCoursePlacesPathToMap(val courseId: Long) : Event()
    }

}

@HiltViewModel
class MapViewModel @Inject constructor(

) : ViewModel(), MapViewModelDelegate {

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)
    override val effect = effectChannel.receiveAsFlow()

    override val state = MutableStateFlow(State())

    private var loadCoursePlacesPathToMapJob: Job? = null

    override fun event(event: Event) {
        when (event) {
            is Event.LoadCoursesWithQuery -> {

            }
            is Event.LoadCoursePlacesPathToMap -> {
                loadCoursePlacesPathToMapJob?.cancel()
                loadCoursePlacesPathToMapJob = viewModelScope.launch {
                    state.emit(State(selectedCoursePlaces = loading()))
                    delay(500)

                    val latRand = { (5600..5700).random() / 10000.0 }

                    val lngRand = { (9600..9800).random() / 10000.0 }

                    state.emit(
                        State(
                            selectedCoursePlaces = success(
                                (0..4 + Random.nextInt(3)).map {
                                    LatLng(37.0 + latRand(), 126.0 + lngRand())
                                }.shuffled()
                            )
                        )
                    )
                }
            }
        }
    }

}