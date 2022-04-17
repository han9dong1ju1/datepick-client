package app.hdj.datepick.presentation.main

import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.model.district.District
import app.hdj.datepick.domain.model.featured.Featured
import app.hdj.datepick.domain.settings.AppSettings
import app.hdj.datepick.domain.usecase.course.GetFirstPageCoursesUseCase
import app.hdj.datepick.domain.usecase.course.params.CourseQueryParams
import app.hdj.datepick.domain.usecase.course.params.CourseQueryWithResult
import app.hdj.datepick.domain.usecase.course.params.courseQueryParams
import app.hdj.datepick.domain.usecase.course.params.pagingParams
import app.hdj.datepick.domain.usecase.district.GetDistrictsUseCase
import app.hdj.datepick.domain.usecase.featured.GetFeaturedListUseCase
import app.hdj.datepick.domain.usecase.place.GetFirstPagePlacesUseCase
import app.hdj.datepick.domain.usecase.place.params.PlaceQueryParams.PagingParams
import app.hdj.datepick.domain.usecase.place.params.PlaceQueryWithResult
import app.hdj.datepick.domain.usecase.place.params.filterParams
import app.hdj.datepick.domain.usecase.place.params.pagingParams
import app.hdj.datepick.domain.usecase.place.params.placeQueryParams
import app.hdj.datepick.presentation.PlatformViewModel
import app.hdj.datepick.presentation.UnidirectionalViewModelDelegate
import app.hdj.datepick.presentation.main.HomeScreenViewModelDelegate.*
import app.hdj.datepick.presentation.utils.toLoadState
import app.hdj.datepick.utils.di.HiltViewModel
import app.hdj.datepick.utils.di.Inject
import app.hdj.datepick.utils.location.LocationTracker
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

interface HomeScreenViewModelDelegate : UnidirectionalViewModelDelegate<State, Effect, Event> {
    val locationTracker: LocationTracker

    data class State(
        val showLocationPermissionBanner: Boolean = false,

        val districts: LoadState<List<District>> = LoadState.idle(),
        val featured: LoadState<List<Featured>> = LoadState.idle(),
        val recommendedCoursesQuery: CourseQueryWithResult? = null,
        val nearbyRecommendedPlacesQuery: PlaceQueryWithResult? = null,
        val recommendedPlacesQuery: PlaceQueryWithResult? = null,
    ) {
        val isContentLoading: Boolean
            get() = districts is LoadState.Loading
                    || featured is LoadState.Loading
                    || recommendedCoursesQuery?.result is LoadState.Loading
                    || recommendedPlacesQuery?.result is LoadState.Loading
                    || nearbyRecommendedPlacesQuery?.result is LoadState.Loading
    }

    sealed class Effect

    sealed class Event {
        object Refresh : Event()

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
    private val getDistrictsUseCase: GetDistrictsUseCase,
    override val locationTracker: LocationTracker,
) : PlatformViewModel(), HomeScreenViewModelDelegate {

    private val effectChannel = Channel<Effect>(Channel.BUFFERED)
    override val effect: Flow<Effect> = effectChannel.receiveAsFlow()

    private val districts = MutableStateFlow<LoadState<List<District>>>(LoadState.idle())
    private val featuredList = MutableStateFlow<LoadState<List<Featured>>>(LoadState.idle())

    private val recommendedCourseList = MutableStateFlow<CourseQueryWithResult?>(null)
    private val recommendedPlaces = MutableStateFlow<PlaceQueryWithResult?>(null)
    private val nearbyRecommendedPlaces = MutableStateFlow<PlaceQueryWithResult?>(null)

    private val isLocationPermissionGranted = MutableStateFlow<Boolean?>(null)

    private val showLocationPermissionBanner = MutableStateFlow(false)

    override val state: StateFlow<State> = app.hdj.datepick.utils.coroutines.combine(
        showLocationPermissionBanner,
        districts,
        featuredList,
        recommendedCourseList,
        recommendedPlaces,
        nearbyRecommendedPlaces
    ) { showLocationPermissionBanner,
        districts,
        featuredListState,
        recommendedCourses,
        recommendedPlacesLoaded,
        nearbyRecommendedPlacesLoaded ->
        State(
            showLocationPermissionBanner,
            districts,
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
        loadDistrictList()

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

            showLocationPermissionBanner.emit(shouldShowNearbyRecommendLocationPermissionBanner)

        }.launchIn(platformViewModelScope)

    }

    private fun loadDistrictList() {
        getDistrictsUseCase()
            .toLoadState()
            .onEach { districts.emit(it) }
            .launchIn(platformViewModelScope)
    }

    private fun loadFeaturedList() {
        getFeaturedListUseCase()
            .toLoadState()
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
            .toLoadState()
            .onEach { recommendedCourseList.emit(CourseQueryWithResult(query, it)) }
            .launchIn(platformViewModelScope)
    }

    private fun loadRecommendedPlaces() {
        val query = placeQueryParams {
            pagingParams { sort = PagingParams.Sort.Popular }
        }

        getFirstPagePlacesUseCase(query)
            .toLoadState()
            .onEach { recommendedPlaces.emit(PlaceQueryWithResult(query, it)) }
            .launchIn(platformViewModelScope)
    }

    private fun loadNearbyRecommendedPlaces() {
        platformViewModelScope.launch {
            val location = locationTracker.getCurrentLocation()

            if (location == null) {
                nearbyRecommendedPlaces.emit(PlaceQueryWithResult(result = LoadState.idle()))
                return@launch
            }

            val query = placeQueryParams {
                filterParams { nearby(location.latitude, location.longitude, 1000.0) }
                pagingParams { sort = PagingParams.Sort.Popular }
            }

            getFirstPagePlacesUseCase(query)
                .toLoadState()
                .onEach { nearbyRecommendedPlaces.emit(PlaceQueryWithResult(query, it)) }
                .launchIn(this)
        }
    }

    override fun event(e: Event) {
        when (e) {
            is Event.Refresh -> loadHomeScreenData()
            is Event.LocationPermissionResult -> platformViewModelScope.launch { isLocationPermissionGranted.emit(e.isGranted) }
            is Event.IgnoreNearbyRecommend -> platformViewModelScope.launch { appSettings.setNearbyRecommendEnabled(true) }
        }
    }
}