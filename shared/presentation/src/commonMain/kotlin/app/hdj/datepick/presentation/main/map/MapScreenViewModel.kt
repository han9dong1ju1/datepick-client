package app.hdj.datepick.presentation.main.map

import app.hdj.datepick.presentation.PlatformViewModel
import app.hdj.datepick.presentation.UnidirectionalViewModelDelegate
import app.hdj.datepick.utils.HiltViewModel
import app.hdj.datepick.utils.Inject
import app.hdj.datepick.utils.location.LocationTracker
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

interface MapScreenViewModelDelegate : UnidirectionalViewModelDelegate<
        MapScreenViewModelDelegate.State,
        MapScreenViewModelDelegate.Effect,
        MapScreenViewModelDelegate.Event
        > {

    class State {

    }

    class Effect {

    }

    class Event {

    }

}

@HiltViewModel
class MapScreenViewModel @Inject constructor(
    private val locationTracker: LocationTracker,
) : MapScreenViewModelDelegate, PlatformViewModel() {

    private val effectChannel = Channel<MapScreenViewModelDelegate.Effect>(Channel.BUFFERED)
    override val effect: Flow<MapScreenViewModelDelegate.Effect> = effectChannel.receiveAsFlow()
    override val state: StateFlow<MapScreenViewModelDelegate.State>
        get() = TODO("Not yet implemented")

    override fun event(e: MapScreenViewModelDelegate.Event) {
        platformViewModelScope.launch {
            when (e) {

            }
        }
    }
}