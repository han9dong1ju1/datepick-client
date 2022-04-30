package app.hdj.datepick.presentation.main

import app.hdj.datepick.domain.usecase.place.GetFirstPagePlacesUseCase
import app.hdj.datepick.domain.usecase.place.params.PlaceQueryWithResult
import app.hdj.datepick.domain.usecase.place.params.filterParams
import app.hdj.datepick.domain.usecase.place.params.placeQueryParams
import app.hdj.datepick.presentation.PlatformViewModel
import app.hdj.datepick.presentation.UnidirectionalViewModelDelegate
import app.hdj.datepick.presentation.main.MapScreenViewModelDelegate.*
import app.hdj.datepick.presentation.utils.toLoadState
import app.hdj.datepick.utils.di.HiltViewModel
import app.hdj.datepick.utils.di.Inject
import app.hdj.datepick.utils.location.LatLng
import app.hdj.datepick.utils.location.LocationTracker
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

interface MapScreenViewModelDelegate : UnidirectionalViewModelDelegate<
        State,
        Effect,
        Event
        > {

    data class State(
        val searchedPlacesState: PlaceQueryWithResult = PlaceQueryWithResult()
    )

    class Effect {

    }

    sealed class Event {
        class ThisLocationSearchClicked(val location: LatLng) : Event()
    }

}

@HiltViewModel
class MapScreenViewModel @Inject constructor(
    private val locationTracker: LocationTracker,
    private val getFirstPagePlacesUseCase: GetFirstPagePlacesUseCase,
) : MapScreenViewModelDelegate, PlatformViewModel() {

    private val effectChannel = Channel<Effect>(Channel.BUFFERED)
    override val effect: Flow<Effect> = effectChannel.receiveAsFlow()

    private val searchedPlacesQuery = MutableStateFlow(PlaceQueryWithResult())

    override val state: StateFlow<State> = searchedPlacesQuery.map {
        State(it)
    }.asStateFlow(State(), platformViewModelScope)

    override fun event(e: Event) {
        platformViewModelScope.launch {
            when (e) {
                is Event.ThisLocationSearchClicked -> {
                    val query = placeQueryParams {
                        filterParams {
                            nearby(e.location.latitude, e.location.longitude, 1000.0)
                        }
                    }
                    getFirstPagePlacesUseCase(query).toLoadState()
                        .onEach { searchedPlacesQuery.emit(PlaceQueryWithResult(query, it)) }
                        .launchIn(this)
                }
            }
        }
    }

}