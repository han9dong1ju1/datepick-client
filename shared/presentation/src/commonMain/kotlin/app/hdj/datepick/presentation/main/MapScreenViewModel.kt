package app.hdj.datepick.presentation.main

import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.model.place.Place
import app.hdj.datepick.domain.usecase.place.GetFirstPagePlacesUseCase
import app.hdj.datepick.domain.usecase.place.GetPlaceCategoriesUseCase
import app.hdj.datepick.domain.usecase.place.params.PlaceQueryParams
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
        val placeCategoriesState: LoadState<List<Place.Category>> = LoadState.idle(),
        val searchedPlacesResult: PlaceQueryWithResult = PlaceQueryWithResult()
    )

    class Effect {

    }

    sealed class Event {
        class ThisLocationSearchClicked(val location: LatLng) : Event()
        class PlaceQueryChanged(val placeQueryParams: PlaceQueryParams) : Event()
    }

}

@HiltViewModel
class MapScreenViewModel @Inject constructor(
    private val locationTracker: LocationTracker,
    private val getFirstPagePlacesUseCase: GetFirstPagePlacesUseCase,
    private val getPlaceCategoriesUseCase: GetPlaceCategoriesUseCase,
) : MapScreenViewModelDelegate, PlatformViewModel() {

    private val effectChannel = Channel<Effect>(Channel.BUFFERED)
    override val effect: Flow<Effect> = effectChannel.receiveAsFlow()

    private val searchedPlacesQuery = MutableStateFlow(PlaceQueryWithResult())

    private val placeCategoriesState = getPlaceCategoriesUseCase().toLoadState()

    override val state: StateFlow<State> = combine(
        placeCategoriesState,
        searchedPlacesQuery,
    ) { placeCategoriesState, searchedPlacesQuery ->
        State(placeCategoriesState, searchedPlacesQuery)
    }.asStateFlow(State(), platformViewModelScope)

    override fun event(e: Event) {
        platformViewModelScope.launch {
            when (e) {
                is Event.ThisLocationSearchClicked -> {
                    val previousQuery = searchedPlacesQuery.value.queryParams
                    val newQuery = previousQuery.copy(
                        filterParams = previousQuery.filterParams.apply {
                            nearby(e.location.latitude, e.location.longitude, distance)
                        }
                    )
                    getFirstPagePlacesUseCase(newQuery).toLoadState()
                        .onEach { searchedPlacesQuery.emit(PlaceQueryWithResult(newQuery, it)) }
                        .launchIn(this)
                }
                is Event.PlaceQueryChanged -> {
                    getFirstPagePlacesUseCase(e.placeQueryParams).toLoadState()
                        .onEach {
                            searchedPlacesQuery.emit(
                                PlaceQueryWithResult(
                                    e.placeQueryParams,
                                    it
                                )
                            )
                        }
                        .launchIn(this)
                }
            }
        }
    }

}