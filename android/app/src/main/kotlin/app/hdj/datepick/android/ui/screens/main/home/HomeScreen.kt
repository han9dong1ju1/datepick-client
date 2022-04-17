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
import app.hdj.datepick.android.utils.*
import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.isStateFailed
import app.hdj.datepick.domain.isStateSucceed
import app.hdj.datepick.domain.model.course.Course
import app.hdj.datepick.domain.model.featured.Featured
import app.hdj.datepick.domain.model.place.Place
import app.hdj.datepick.domain.onSucceed
import app.hdj.datepick.domain.usecase.course.params.CourseQueryParams
import app.hdj.datepick.domain.usecase.place.params.PlaceQueryParams
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
        onMorePlaceListClicked = navigator.onMorePlaceListClicked,
        onMoreCourseListClicked = navigator.onMoreCourseListClicked,
        onNotificationClicked = { navigator.navigate(NotificationScreenDestination) },
        onLocationPermissionDeniedDialogShown = { navigator.navigate(LocationPermissionDeniedDialogDestination) },
        vm = hiltViewModel<HomeScreenViewModel>()
    )
}

@Composable
private fun HomeScreenContent(
    onPlaceClicked: (Place) -> Unit = {},
    onMorePlaceListClicked: (PlaceQueryParams) -> Unit = {},
    onMoreCourseListClicked: (CourseQueryParams) -> Unit = {},
    onCourseClicked: (Course) -> Unit = {},
    onNotificationClicked: () -> Unit = {},
    onFeaturedClicked: (Featured) -> Unit = {},
    onLocationPermissionDeniedDialogShown: () -> Unit = {},
    vm: HomeScreenViewModelDelegate
) {

    val (state, effect, event) = vm.extract()

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

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

            mainHomeFeatured(state.featured, event, onFeaturedClicked)

            state.apply {

                nearbyRecommendedPlacesQuery?.run {
                    result.onSucceed { places ->
                        itemHorizontalPlacesWithHeader("주변 인기 장소",
                            places,
                            onPlaceClicked,
                            onMoreClicked = { onMorePlaceListClicked(queryParams) }
                        )
                    }
                }

                recommendedPlacesQuery?.run {
                    result.onSucceed { places ->
                        itemHorizontalPlacesWithHeader(
                            "추천 인기 장소",
                            places,
                            onPlaceClicked, onMoreClicked = { onMorePlaceListClicked(queryParams) }
                        )
                    }
                }


                if (showNearbyRecommendLocationPermissionBanner) {
                    mainHomeLocationPermissionBanner(
                        activity,
                        onLocationPermissionDeniedDialogShown,
                        locationPermissionRequest,
                        event
                    )
                }

            }

            state.recommendedCoursesQuery?.run {
                result.onSucceed { courses ->
                    itemHorizontalCoursesWithHeader(
                        "추천 코스",
                        courses,
                        onCourseClicked,
                        onMoreClicked = { onMoreCourseListClicked(queryParams) }
                    )
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
    featured: LoadState<List<Featured>>,
    event: (HomeScreenViewModelDelegate.Event) -> Unit,
    onFeaturedClicked: (Featured) -> Unit
) {
    item {
        Crossfade(
            targetState = featured,
            modifier = Modifier.fillMaxWidth().animateItemPlacement().animateContentSize()
        ) {
            if (it.isStateFailed()) {
                RetryBanner(modifier = Modifier.animateItemPlacement()) {
                    event(HomeScreenViewModelDelegate.Event.RetryFeatured)
                }
            } else if (it.isStateSucceed()) {
                Column(modifier = Modifier.fillMaxWidth().animateItemPlacement()) {
                    Spacer(modifier = Modifier.height(20.dp))
                    ViewPager(
                        Modifier.fillMaxWidth(),
                        it.data,
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