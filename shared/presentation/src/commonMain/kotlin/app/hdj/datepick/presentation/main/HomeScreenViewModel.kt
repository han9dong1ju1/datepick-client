package app.hdj.datepick.presentation.main

import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.model.featured.Featured
import app.hdj.datepick.domain.settings.AppSettings
import app.hdj.datepick.domain.usecase.course.GetFirstPageCoursesUseCase
import app.hdj.datepick.domain.usecase.course.params.CourseQueryParams
import app.hdj.datepick.domain.usecase.course.params.CourseQueryResult
import app.hdj.datepick.domain.usecase.course.params.courseQueryParams
import app.hdj.datepick.domain.usecase.course.params.pagingParams
import app.hdj.datepick.domain.usecase.featured.GetFeaturedListUseCase
import app.hdj.datepick.domain.usecase.place.GetFirstPagePlacesUseCase
import app.hdj.datepick.domain.usecase.place.params.PlaceQueryParams.PagingParams
import app.hdj.datepick.domain.usecase.place.params.PlaceQueryResult
import app.hdj.datepick.domain.usecase.place.params.filterParams
import app.hdj.datepick.domain.usecase.place.params.pagingParams
import app.hdj.datepick.domain.usecase.place.params.placeQueryParams
import app.hdj.datepick.presentation.PlatformViewModel
import app.hdj.datepick.presentation.UnidirectionalViewModelDelegate
import app.hdj.datepick.presentation.main.HomeScreenViewModelDelegate.*
import app.hdj.datepick.utils.di.HiltViewModel
import app.hdj.datepick.utils.di.Inject
import app.hdj.datepick.utils.location.LatLng
import app.hdj.datepick.utils.location.LocationTracker
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

interface HomeScreenViewModelDelegate : UnidirectionalViewModelDelegate<State, Effect, Event> {
    val locationTracker: LocationTracker

    data class State(
        val showNearbyRecommendLocationPermissionBanner: Boolean = false,

        val featured: LoadState<List<Featured>> = LoadState.idle(),
        val recommendedCoursesQuery: CourseQueryResult? = null,
        val nearbyRecommendedPlacesQuery: PlaceQueryResult? = null,
        val recommendedPlacesQuery: PlaceQueryResult? = null,
    ) {

        val isContentRefreshing: Boolean
            get() = featured is LoadState.Loading
                    || recommendedCoursesQuery?.result is LoadState.Loading
                    || recommendedPlacesQuery?.result is LoadState.Loading
                    || nearbyRecommendedPlacesQuery?.result is LoadState.Loading
    }

    sealed class Effect {
        object LocationPermissionRevoked : Effect()
    }

    sealed class Event {
        object Refresh : Event()

        object RetryFeatured : Event()

        object IgnoreNearbyRecommend : Event()
        class LocationPermissionResult(val isGranted: Boolean) : Event()
    }

}

@FlowPreview
@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val appSettings: AppSettings,
    private val getFeaturedListUseCase: GetFeaturedListUseCase,
    private val getFirstPageCoursesUseCase: GetFirstPageCoursesUseCase,
    private val getFirstPagePlacesUseCase: GetFirstPagePlacesUseCase,
    override val locationTracker: LocationTracker,
) : PlatformViewModel(), HomeScreenViewModelDelegate {

    private val effectChannel = Channel<Effect>(Channel.BUFFERED)
    override val effect: Flow<Effect> = effectChannel.receiveAsFlow()

    private val featuredList = MutableStateFlow<LoadState<List<Featured>>>(LoadState.idle())
    private val recommendedCourseList = MutableStateFlow<CourseQueryResult?>(null)
    private val recommendedPlaces = MutableStateFlow<PlaceQueryResult?>(null)
    private val nearbyRecommendedPlaces = MutableStateFlow<PlaceQueryResult?>(null)

    private val isLocationPermissionGranted = MutableStateFlow<Boolean?>(null)

    private val showNearbyRecommendLocationPermissionBanner = MutableStateFlow(false)

    override val state: StateFlow<State> = combine(
        showNearbyRecommendLocationPermissionBanner,
        featuredList,
        recommendedCourseList,
        recommendedPlaces,
        nearbyRecommendedPlaces
    ) { showNearbyRecommendLocationPermissionBanner,
        featuredListState,
        recommendedCourses,
        recommendedPlacesLoaded,
        nearbyRecommendedPlacesLoaded ->

        State(
            showNearbyRecommendLocationPermissionBanner,
            featuredListState,
            recommendedCourses,
            recommendedPlacesLoaded,
            nearbyRecommendedPlacesLoaded
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
        loadRecommendedCourses()
        loadRecommendedPlaces()

        combine(
            appSettings.isNearbyRecommendEnabled,
            isLocationPermissionGranted.filterNotNull()
        ) { isNearbyRecommendEnabled,
            isLocationPermissionGranted ->

            val canShowNearbyRecommendations = isNearbyRecommendEnabled && isLocationPermissionGranted
            val shouldShowNearbyRecommendLocationPermissionBanner =
                isNearbyRecommendEnabled && !isLocationPermissionGranted

            if (canShowNearbyRecommendations) loadNearbyRecommendedPlaces()
            else nearbyRecommendedPlaces.emit(null)

            showNearbyRecommendLocationPermissionBanner.emit(shouldShowNearbyRecommendLocationPermissionBanner)

        }.launchIn(platformViewModelScope)

    }

    private fun loadFeaturedList() {
        getFeaturedListUseCase(Unit)
            .onEach { featuredList.emit(it) }
            .launchIn(platformViewModelScope)
    }

    private fun loadRecommendedCourses() {
        val query = courseQueryParams {
            pagingParams {
                sort = CourseQueryParams.PagingParams.Sort.Popular
            }
        }

        getFirstPageCoursesUseCase(query)
            .onEach { recommendedCourseList.emit(CourseQueryResult(query, it)) }
            .launchIn(platformViewModelScope)
    }

    private fun loadRecommendedPlaces() {
        val query = placeQueryParams {
            pagingParams { sort = PagingParams.Sort.Popular }
        }

        getFirstPagePlacesUseCase(query)
            .onEach { recommendedPlaces.emit(PlaceQueryResult(query, it)) }
            .launchIn(platformViewModelScope)
    }

    private fun loadNearbyRecommendedPlaces() {
        platformViewModelScope.launch {
            val location = locationTracker.getCurrentLocation()

            if (location == null) {
                nearbyRecommendedPlaces.emit(PlaceQueryResult(result = LoadState.idle()))
                return@launch
            }

            val query = placeQueryParams {
                filterParams { nearby(location.latitude, location.longitude, 1000.0) }
                pagingParams { sort = PagingParams.Sort.Popular }
            }
            getFirstPagePlacesUseCase(query)
                .onEach { nearbyRecommendedPlaces.emit(PlaceQueryResult(query, it)) }
                .launchIn(platformViewModelScope)
        }
    }

    override fun event(e: Event) {
        platformViewModelScope.launch {
            when (e) {

                is Event.Refresh -> loadHomeScreenData()

                is Event.RetryFeatured -> loadFeaturedList()

                is Event.LocationPermissionResult -> isLocationPermissionGranted.emit(e.isGranted)

                is Event.IgnoreNearbyRecommend -> appSettings.setNearbyRecommendEnabled(true)
            }
        }
    }
}