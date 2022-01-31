package app.hdj.datepick.presentation.main

import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.isStateFailed
import app.hdj.datepick.domain.isStateLoading
import app.hdj.datepick.domain.isStateSucceed
import app.hdj.datepick.domain.model.featured.Featured
import app.hdj.datepick.domain.settings.AppSettings
import app.hdj.datepick.domain.usecase.featured.GetFeaturedListUseCase
import app.hdj.datepick.presentation.PlatformViewModel
import app.hdj.datepick.presentation.UnidirectionalViewModelDelegate
import app.hdj.datepick.presentation.main.HomeScreenViewModelDelegate.*
import app.hdj.datepick.utils.HiltViewModel
import app.hdj.datepick.utils.Inject
import app.hdj.datepick.utils.location.LatLng
import app.hdj.datepick.utils.location.LocationTracker
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

interface HomeScreenViewModelDelegate : UnidirectionalViewModelDelegate<State, Effect, Event> {
    val locationTracker: LocationTracker

    class State(
        val isContentRefreshing: Boolean = false,

        val featuredUiState: FeaturedUiState = FeaturedUiState(),
        val nearbyRecommendationsUiState: NearbyRecommendationsUiState = NearbyRecommendationsUiState(),
    ) {

        class FeaturedUiState(
            val showFeaturedList: Boolean = false,
            val showFeaturedFailedBanner : Boolean = false,
            val featuredList: List<Featured> = emptyList(),
        )

        class NearbyRecommendationsUiState(
            val showNearbyRecommendLocationPermissionBanner: Boolean = false,
            val showNearbyRecommendations: Boolean = false,

        )

    }

    sealed class Effect {
        object LocationPermissionRevoked : Effect()
    }

    sealed class Event {
        object Refresh : Event()

        object RetryFeaturedList : Event()

        object IgnoreNearbyRecommend : Event()
        class LocationPermissionResult(val isGranted: Boolean) : Event()
        class CurrentLocation(val latLng: LatLng) : Event()
    }

}

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getFeaturedListUseCase: GetFeaturedListUseCase,
    override val locationTracker: LocationTracker,
    private val appSettings: AppSettings
) : PlatformViewModel(), HomeScreenViewModelDelegate {

    private val effectChannel = Channel<Effect>(Channel.BUFFERED)
    override val effect: Flow<Effect> = effectChannel.receiveAsFlow()

    private val featuredList = MutableStateFlow<LoadState<List<Featured>>>(LoadState.loading())

    private val currentLocation = MutableStateFlow<LatLng?>(null)

    private val isLocationPermissionGranted = MutableStateFlow<Boolean?>(null)

    override val state: StateFlow<State> = combine(
        appSettings.ignoreNearbyRecommend,
        isLocationPermissionGranted,
        featuredList,
    ) { ignoreNearbyRecommend,
        isLocationPermissionGranted,
        featuredListState ->

        val isContentRefreshing = featuredListState.isStateLoading()

        val featuredUiState = State.FeaturedUiState(
            featuredListState.isStateSucceed(),
            featuredListState.isStateFailed(),
            featuredListState.getDataOrNull().orEmpty()
        )

        val nearbyRecommendationsUiState = State.NearbyRecommendationsUiState(
            ignoreNearbyRecommend == null && isLocationPermissionGranted == false,
            isLocationPermissionGranted == true
        )

        State(
            isContentRefreshing,
            featuredUiState,
            nearbyRecommendationsUiState
        )
    }.asStateFlow(
        State(),
        platformViewModelScope
    )

    init {
        loadHomeScreenData()
    }

    private fun loadHomeScreenData() {
        loadFeaturedList()

    }

    private fun loadFeaturedList() {
        getFeaturedListUseCase(Unit)
            .onEach { featuredList.emit(it) }
            .launchIn(platformViewModelScope)
    }

    override fun event(e: Event) {
        platformViewModelScope.launch {
            when (e) {
                is Event.Refresh -> {
                    loadHomeScreenData()
                }

                is Event.RetryFeaturedList -> loadFeaturedList()

                is Event.LocationPermissionResult -> {
                    isLocationPermissionGranted.emit(e.isGranted)
                }
                is Event.IgnoreNearbyRecommend -> {
                    appSettings.setIgnoreNearbyRecommend(true)
                }
                is Event.CurrentLocation -> {
                    currentLocation.emit(e.latLng)
                }
            }
        }
    }
}