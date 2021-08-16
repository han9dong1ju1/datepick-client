package app.hdj.datepick.android.ui.components.screens.main.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.hdj.datepick.android.ui.components.screens.main.map.MapViewModelDelegate.*
import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.LoadState.Companion.loading
import app.hdj.datepick.domain.isStateLoading
import app.hdj.datepick.ui.utils.ViewModelDelegate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

fun fakeMapViewModel() = object : MapViewModelDelegate {

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)

    override val state = MutableStateFlow(State())

    override val effect = effectChannel.receiveAsFlow()

    override fun event(event: Event) {

    }

}

interface MapViewModelDelegate : ViewModelDelegate<State, Effect, Event> {

    data class State(
        val mapPathState : LoadState<List<String>> = loading()
    ) {
        val showProgressBar get() =
            mapPathState.isStateLoading()
    }

    sealed class Effect {

    }

    sealed class Event {
        class LoadCoursePlacesPathToMap(val courseId: Long) : Event()
    }

}

@HiltViewModel
class MapViewModel @Inject constructor(

) : ViewModel(), MapViewModelDelegate {

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)
    override val effect = effectChannel.receiveAsFlow()

    override val state: StateFlow<State> = MutableStateFlow(State())

    private var loadCoursePlacesPathToMapJob: Job? = null

    override fun event(event: Event) {
        when (event) {
            is Event.LoadCoursePlacesPathToMap -> {
                loadCoursePlacesPathToMapJob?.cancel()
                loadCoursePlacesPathToMapJob = viewModelScope.launch {
                    delay(500)

                }
            }
        }
    }

}