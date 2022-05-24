package app.hdj.datepick.presentation.placelist

import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.model.place.Place
import app.hdj.datepick.domain.usecase.place.GetPlaceCategoriesUseCase
import app.hdj.datepick.domain.usecase.place.SearchPlacesUseCase
import app.hdj.datepick.domain.usecase.place.params.PlaceQueryParams
import app.hdj.datepick.domain.usecase.place.params.PlaceQueryWithPagingResult
import app.hdj.datepick.presentation.PlatformViewModel
import app.hdj.datepick.presentation.UnidirectionalViewModelDelegate
import app.hdj.datepick.presentation.placelist.PlaceListScreenViewModelDelegate.*
import app.hdj.datepick.presentation.utils.toLoadState
import app.hdj.datepick.utils.di.HiltViewModel
import app.hdj.datepick.utils.di.Inject
import com.kuuurt.paging.multiplatform.helpers.cachedIn
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

interface PlaceListScreenViewModelDelegate : UnidirectionalViewModelDelegate<State, Effect, Event> {

    data class State(
        val placeCategoriesState : LoadState<List<Place.Category>> = LoadState.idle(),
        val placesQueryResult: PlaceQueryWithPagingResult = PlaceQueryWithPagingResult(),
    )

    sealed interface Effect {

    }

    sealed interface Event {
        data class SearchPlaces(val params: PlaceQueryParams) : Event
        object Refresh : Event
    }

}

@HiltViewModel
class PlaceListScreenViewModel @Inject constructor(
    searchPlacesUseCase: SearchPlacesUseCase,
    getPlaceCategoriesUseCase: GetPlaceCategoriesUseCase,
) : PlatformViewModel(), PlaceListScreenViewModelDelegate {

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)
    override val effect: Flow<Effect> = effectChannel.receiveAsFlow()

    private val placeQueryParams = MutableStateFlow<PlaceQueryParams?>(null)

    private val placesQueryResult = placeQueryParams
        .filterNotNull()
        .map {
            PlaceQueryWithPagingResult(
                it,
                searchPlacesUseCase(it).pagingData.cachedIn(platformViewModelScope),
            )
        }

    override val state: StateFlow<State> = combine(
        getPlaceCategoriesUseCase().toLoadState(),
        placesQueryResult,
    ) { categoriesState, placesQueryResult ->
        State(categoriesState, placesQueryResult)
    }.asStateFlow(State(), platformViewModelScope)

    private suspend fun load(params: PlaceQueryParams?) {
        placeQueryParams.emit(params)
    }

    override fun event(e: Event) {
        platformViewModelScope.launch {
            when (e) {
                is Event.SearchPlaces -> load(e.params)
                Event.Refresh -> {
                    placeQueryParams.emit(null)
                    load(placeQueryParams.value)
                }
            }
        }
    }
}