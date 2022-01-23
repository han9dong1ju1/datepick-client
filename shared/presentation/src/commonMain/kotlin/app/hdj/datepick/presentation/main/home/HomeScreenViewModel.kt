package app.hdj.datepick.presentation.main.home

import app.hdj.datepick.domain.isStateFailed
import app.hdj.datepick.domain.model.featured.Featured
import app.hdj.datepick.domain.settings.AppSettings
import app.hdj.datepick.domain.usecase.featured.GetFeaturedListUseCase
import app.hdj.datepick.presentation.PlatformViewModel
import app.hdj.datepick.presentation.UnidirectionalViewModelDelegate
import app.hdj.datepick.presentation.main.home.HomeScreenViewModelDelegate.*
import app.hdj.datepick.utils.HiltViewModel
import app.hdj.datepick.utils.Inject
import app.hdj.datepick.utils.PlatformLogger
import app.hdj.datepick.utils.location.LatLng
import app.hdj.datepick.utils.location.LocationTracker
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

interface HomeScreenViewModelDelegate : UnidirectionalViewModelDelegate<State, Effect, Event> {
    val locationTracker: LocationTracker

    class State(
        val featuredList: List<Featured> = emptyList(),
        val showNearbyRecommendLocationPermissionBanner: Boolean = false,
        val showNearbyRecommendations: Boolean = false
    )

    sealed class Effect {
        object FeaturedError : Effect()
        object LocationPermissionRevoked : Effect()
    }

    sealed class Event {
        object IgnoreNearbyRecommend : Event()
        class LocationPermissionResult(val isGranted: Boolean) : Event()
        class CurrentLocation(val latLng: LatLng) : Event()
    }

}

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    getFeaturedListUseCase: GetFeaturedListUseCase,
    override val locationTracker: LocationTracker,
    private val appSettings: AppSettings
) : PlatformViewModel(), HomeScreenViewModelDelegate {

    private val effectChannel = Channel<Effect>(Channel.BUFFERED)
    override val effect: Flow<Effect> = effectChannel.receiveAsFlow()

    private val featuredList = getFeaturedListUseCase(Unit).onEach {
        if (it.isStateFailed()) effectChannel.send(Effect.FeaturedError)
    }

    private val currentLocation = MutableStateFlow<LatLng?>(null)

    private val isLocationPermissionGranted = MutableStateFlow<Boolean?>(null)

    override val state: StateFlow<State> = combine(
        appSettings.ignoreNearbyRecommend,
        isLocationPermissionGranted,
        featuredList,
    ) { ignoreNearbyRecommend,
        isLocationPermissionGranted,
        featuredListState ->
        State(
            featuredListState.getDataOrNull().orEmpty(),
            ignoreNearbyRecommend == null && isLocationPermissionGranted == false,
            ignoreNearbyRecommend != true && isLocationPermissionGranted == true
        )
    }.asStateFlow(
        State(),
        platformViewModelScope
    )

    override fun event(e: Event) {
        platformViewModelScope.launch {
            when (e) {
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