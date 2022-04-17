package app.hdj.datepick.presentation.place

import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.isStateLoading
import app.hdj.datepick.domain.model.place.Place
import app.hdj.datepick.domain.usecase.place.GetFirstPagePlacesUseCase
import app.hdj.datepick.domain.usecase.place.GetPlaceDetailUseCase
import app.hdj.datepick.domain.usecase.place.params.PlaceQueryParams
import app.hdj.datepick.domain.usecase.place.params.PlaceQueryWithResult
import app.hdj.datepick.domain.usecase.place.params.filterParams
import app.hdj.datepick.domain.usecase.place.params.placeQueryParams
import app.hdj.datepick.presentation.PlatformViewModel
import app.hdj.datepick.presentation.UnidirectionalViewModelDelegate
import app.hdj.datepick.presentation.place.PlaceDetailScreenViewModelDelegate.*
import app.hdj.datepick.presentation.utils.toLoadState
import app.hdj.datepick.utils.di.HiltViewModel
import app.hdj.datepick.utils.di.Inject
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*

interface PlaceDetailScreenViewModelDelegate : UnidirectionalViewModelDelegate<State, Effect, Event> {

    data class State(
        val placeState: LoadState<Place> = LoadState.idle(),
        val recommendedPlacesQueryResult: PlaceQueryWithResult = PlaceQueryWithResult(),
    ) {
        val isLoading: Boolean
            get() = placeState.isStateLoading() || recommendedPlacesQueryResult.result.isStateLoading()
    }

    class Effect

    sealed interface Event {
        data class SetPlace(val place: Place) : Event
        data class LoadPlaceWithId(val id: Long) : Event

        object Refresh : Event
    }

}

@OptIn(FlowPreview::class)
@HiltViewModel
class PlaceDetailScreenViewModel @Inject constructor(
    private val getPlaceDetailUseCase: GetPlaceDetailUseCase,
    private val getFirstPagePlacesUseCase: GetFirstPagePlacesUseCase
) : PlatformViewModel(), PlaceDetailScreenViewModelDelegate {

    private val effectChannel = Channel<Effect>(Channel.UNLIMITED)
    override val effect: Flow<Effect> = effectChannel.receiveAsFlow()

    private val placeState = MutableStateFlow<LoadState<Place>>(LoadState.loading())

    private val recommendedPlacesQuery = MutableStateFlow<PlaceQueryParams?>(null)
    private val recommendedPlacesQueryResult = recommendedPlacesQuery
        .filterNotNull()
        .flatMapConcat { getFirstPagePlacesUseCase(it).toLoadState() }
        .map { PlaceQueryWithResult(queryParams = requireNotNull(recommendedPlacesQuery.value), result = it) }

    override val state = combine(
        placeState, recommendedPlacesQueryResult
    ) { placeState, recommendedPlacesQueryResult ->
        State(
            placeState = placeState,
            recommendedPlacesQueryResult = recommendedPlacesQueryResult
        )
    }.asStateFlow(State(), platformViewModelScope)

    init {
        placeState.onEach {
            val place = it.dataOrNull
            if (place != null) {
                recommendedPlacesQuery.emit(
                    placeQueryParams {
                        filterParams {
                            nearby(place.latitude, place.longitude, 1000.0)
                            categoryIds = place.categories.map(Place.Category::id)
                        }
                    }
                )
            }
        }.launchIn(platformViewModelScope)
    }

    private fun load(placeId: Long) {
        getPlaceDetailUseCase(placeId)
            .toLoadState()
            .onEach { placeState.emit(it) }
            .launchIn(platformViewModelScope)
    }

    override fun event(e: Event) {
        when (e) {
            is Event.Refresh -> placeState.value.dataOrNull?.id?.let { load(placeId = it) }
            is Event.SetPlace -> placeState.tryEmit(LoadState.success(e.place))
            is Event.LoadPlaceWithId -> load(e.id)
        }
    }
}