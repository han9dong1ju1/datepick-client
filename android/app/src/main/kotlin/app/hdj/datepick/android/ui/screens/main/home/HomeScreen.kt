package app.hdj.datepick.android.ui.screens.main.home

import android.Manifest
import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import app.hdj.datepick.android.ui.components.list.*
import app.hdj.datepick.android.ui.destinations.*
import app.hdj.datepick.android.ui.icons.DatePickIcons
import app.hdj.datepick.android.ui.icons.Logo
import app.hdj.datepick.android.ui.providers.LocalLocationTracker
import app.hdj.datepick.android.ui.providers.preview.FakePlacePreviewProvider
import app.hdj.datepick.android.utils.extract
import app.hdj.datepick.android.utils.onCourseClicked
import app.hdj.datepick.android.utils.onFeaturedClicked
import app.hdj.datepick.android.utils.onPlaceClicked
import app.hdj.datepick.domain.model.course.Course
import app.hdj.datepick.domain.model.featured.Featured
import app.hdj.datepick.domain.model.place.Place
import app.hdj.datepick.presentation.main.HomeScreenViewModel
import app.hdj.datepick.presentation.main.HomeScreenViewModelDelegate
import app.hdj.datepick.presentation.main.HomeScreenViewModelDelegate.Event.LocationPermissionResult
import app.hdj.datepick.ui.components.BaseSwipeRefreshLayoutScaffold
import app.hdj.datepick.ui.components.InsetTopBar
import app.hdj.datepick.ui.components.ViewPager
import app.hdj.datepick.ui.utils.getActivity
import app.hdj.datepick.ui.utils.isPermissionGranted
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination(start = true)
fun HomeScreen(navigator: DestinationsNavigator) {
    HomeScreenContent(
        onPlaceClicked = navigator.onPlaceClicked,
        onCourseClicked = navigator.onCourseClicked,
        onFeaturedClicked = navigator.onFeaturedClicked,
        onNotificationClicked = { navigator.navigate(NotificationScreenDestination) },
        onLocationPermissionDeniedDialogShown = { navigator.navigate(LocationPermissionDeniedDialogDestination) },
        vm = hiltViewModel<HomeScreenViewModel>()
    )
}


@Composable
private fun HomeScreenContent(
    onPlaceClicked: (Place) -> Unit = {},
    onCourseClicked: (Course) -> Unit = {},
    onNotificationClicked: () -> Unit = {},
    onFeaturedClicked: (Featured) -> Unit = {},
    onLocationPermissionDeniedDialogShown: () -> Unit = {},
    vm: HomeScreenViewModelDelegate
) {

    val (state, effect, event) = vm.extract()

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val locationTracker = LocalLocationTracker.current

    BackHandler {
        context.getActivity()?.finish()
    }

    val swipeRefreshState = rememberSwipeRefreshState(state.isContentRefreshing)

    val lazyListState = rememberLazyListState()

    val activity = remember { requireNotNull(context.getActivity()) }

    val locationPermissionRequest = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        event(LocationPermissionResult(granted))
    }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_RESUME -> {
                    event(LocationPermissionResult(activity.isPermissionGranted(Manifest.permission.ACCESS_FINE_LOCATION)))
                }
                Lifecycle.Event.ON_PAUSE -> {
                }
                else -> {

                }
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    LaunchedEffect(state.nearbyRecommendationsUiState.showNearbyRecommendations) {
        if (state.nearbyRecommendationsUiState.showNearbyRecommendations) {
            val currentLocation = locationTracker.getCurrentLocation()
            if (currentLocation != null) event(HomeScreenViewModelDelegate.Event.CurrentLocation(currentLocation))
        }
    }

    BaseSwipeRefreshLayoutScaffold(
        modifier = Modifier.fillMaxSize(),
        swipeRefreshState = swipeRefreshState,
        onRefresh = { event(HomeScreenViewModelDelegate.Event.Refresh) },
        topBar = {
            InsetTopBar(
                title = {
                    Icon(DatePickIcons.Logo, null, modifier = Modifier.height(18.dp))
                },
                actions = {
                    IconButton(onNotificationClicked) {
                        Icon(imageVector = Icons.Rounded.Notifications, null)
                    }
                },
                enableDivider = lazyListState.firstVisibleItemIndex != 0
            )
        }
    ) {

        LazyColumn(modifier = Modifier.padding(it), state = lazyListState) {

            state.featuredUiState.apply {
                mainHomeFeatured(this, event, onFeaturedClicked)
            }

            state.nearbyRecommendationsUiState.apply {
                if (showNearbyRecommendations) {
                    mainHomeNearbyRecommendedPlaces(onPlaceClicked)
                    mainHomePopularPlaces(onPlaceClicked)
                } else {
                    mainHomePopularPlaces(onPlaceClicked)
                    if (showNearbyRecommendLocationPermissionBanner) {
                        mainHomeLocationPermissionBanner(
                            activity,
                            onLocationPermissionDeniedDialogShown,
                            locationPermissionRequest,
                            event
                        )
                    }
                }
            }

            state.recommendedCoursesUiState.apply {
                if (showRecommendedCourses) {
                    mainRecommendedCourses(recommendedCourses, onCourseClicked)
                }
            }

            state.recommendedCoursesUiState.apply {
                if (showRecommendedCourses) {
                    mainRecommendedCourses(recommendedCourses, onCourseClicked)
                }
            }

            item {
                Spacer(modifier = Modifier.navigationBarsPadding())
                Spacer(modifier = Modifier.height(100.dp))
            }

        }

    }

}

private fun LazyListScope.mainHomeFeatured(
    featuredUiState: HomeScreenViewModelDelegate.State.FeaturedUiState,
    event: (HomeScreenViewModelDelegate.Event) -> Unit,
    onFeaturedClicked: (Featured) -> Unit
) {
    item {
        Crossfade(
            targetState = featuredUiState,
            modifier = Modifier.fillMaxWidth().animateItemPlacement().animateContentSize()
        ) {
            if (it.showFeaturedFailedBanner) {
                RetryBanner(modifier = Modifier.animateItemPlacement()) {
                    event(HomeScreenViewModelDelegate.Event.RetryFeaturedList)
                }
            } else if (it.showFeaturedList) {
                Column(modifier = Modifier.fillMaxWidth().animateItemPlacement()) {
                    Spacer(modifier = Modifier.height(20.dp))
                    ViewPager(
                        Modifier.fillMaxWidth(),
                        it.featuredList,
                        itemSpacing = 10.dp,
                        contentPadding = PaddingValues(horizontal = 20.dp),
                    ) { item, _ ->
                        FeaturedListItem(featured = item, onFeaturedClicked)
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                }
            } else {
                FeaturedListItemShimmer()
            }
        }
    }
}

private fun LazyListScope.mainHomeLocationPermissionBanner(
    activity: Activity,
    onLocationPermissionDeniedDialogShown: () -> Unit,
    locationPermissionRequest: ManagedActivityResultLauncher<String, Boolean>,
    event: (HomeScreenViewModelDelegate.Event) -> Unit
) {
    item {
        MainHomeLocationPermissionBanner(
            Modifier.animateItemPlacement().fillMaxWidth(),
            {
                val shouldShowRequestPermissionRationale =
                    activity.shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)

                val isPermissionGranted =
                    activity.isPermissionGranted(Manifest.permission.ACCESS_FINE_LOCATION)

                val showDeniedDialog =
                    (!isPermissionGranted && shouldShowRequestPermissionRationale)

                if (showDeniedDialog) onLocationPermissionDeniedDialogShown()
                else locationPermissionRequest.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            },
            { event(HomeScreenViewModelDelegate.Event.IgnoreNearbyRecommend) },
        )
    }
}

private fun LazyListScope.mainHomePopularPlaces(onPlaceClicked: (Place) -> Unit) {
    item {
        Column(modifier = Modifier.fillMaxWidth().animateItemPlacement()) {
            val list = remember { FakePlacePreviewProvider().values.first() }
            Spacer(modifier = Modifier.height(10.dp))
            Header("전체 인기장소", "더보기") {

            }
            PlaceHorizontalList(list, onPlaceClicked)
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

private fun LazyListScope.mainHomeNearbyRecommendedPlaces(onPlaceClicked: (Place) -> Unit) {
    item {
        Column(modifier = Modifier.fillMaxWidth().animateItemPlacement()) {
            val list = remember { FakePlacePreviewProvider().values.first() }
            Spacer(modifier = Modifier.height(10.dp))
            Header("주변 추천 인기장소", "더보기") {

            }
            PlaceHorizontalList(list, onPlaceClicked)
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

private fun LazyListScope.mainRecommendedCourses(
    recommendedCourses: List<Course>,
    onCourseClicked: (Course) -> Unit
) {
    item {
        Column(modifier = Modifier.fillMaxWidth().animateItemPlacement()) {
            Spacer(modifier = Modifier.height(10.dp))
            Header("추천 인기코스", "더보기") {

            }
            LazyRow(contentPadding = PaddingValues(start = 20.dp)) {
                items(recommendedCourses) { it ->
                    CourseHorizontalListItem(it, onCourseClicked)
                    Spacer(Modifier.width(20.dp))
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}