package app.hdj.datepick.android.ui.screens.main.home

import android.Manifest
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import app.hdj.datepick.android.ui.components.list.*
import app.hdj.datepick.android.ui.destinations.LocationPermissionDeniedDialogDestination
import app.hdj.datepick.android.ui.destinations.NotificationScreenDestination
import app.hdj.datepick.android.ui.icons.DatePickIcons
import app.hdj.datepick.android.ui.icons.Logo
import app.hdj.datepick.android.ui.providers.WindowSize
import app.hdj.datepick.android.ui.providers.rememberWindowSizeClass
import app.hdj.datepick.android.utils.*
import app.hdj.datepick.domain.model.course.Course
import app.hdj.datepick.domain.model.district.District
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
import app.hdj.datepick.ui.utils.getActivity
import app.hdj.datepick.ui.utils.isPermissionGranted
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph(start = true)
@Destination
@Composable
fun HomeScreen(navigator: DestinationsNavigator) {
    HomeScreenContent(
        onPlaceClicked = navigator.onPlaceClicked,
        onCourseClicked = navigator.onCourseClicked,
        onFeaturedClicked = navigator.onFeaturedClicked,
        onMorePlaceListClicked = navigator.onMorePlaceListClicked,
        onMoreCourseListClicked = navigator.onMoreCourseListClicked,
        onMoreFeaturedListClicked = navigator.onMoreFeaturedListClicked,
        onNotificationClicked = { navigator.navigate(NotificationScreenDestination) },
        onLocationPermissionDeniedDialogShown = {
            navigator.navigate(LocationPermissionDeniedDialogDestination)
        },
        vm = hiltViewModel<HomeScreenViewModel>()
    )
}

@Composable
private fun HomeScreenContent(
    onPlaceClicked: (Place) -> Unit = {},
    onMorePlaceListClicked: (PlaceQueryParams) -> Unit = {},
    onMoreCourseListClicked: (CourseQueryParams) -> Unit = {},
    onMoreFeaturedListClicked: () -> Unit = {},
    onCourseClicked: (Course) -> Unit = {},
    onNotificationClicked: () -> Unit = {},
    onFeaturedClicked: (Featured) -> Unit = {},
    onDistrictClicked: (District) -> Unit = {},
    onLocationPermissionDeniedDialogShown: () -> Unit = {},
    vm: HomeScreenViewModelDelegate
) {

    val (state, effect, event) = vm.extract()

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    BackHandler {
        context.getActivity()?.finish()
    }

    val swipeRefreshState = rememberSwipeRefreshState(state.isContentLoading)

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
                else -> Unit
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

        Column(
            modifier = Modifier
                .padding(it)
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
        ) {
            state.apply {

                Spacer(Modifier.height(40.dp))
                Text(
                    modifier = Modifier
                        .padding(20.dp),
                    text = "오늘 당신의\n데이트 장소를 찾아보세요!",
                    style = MaterialTheme.typography.h1,
                )
                Spacer(Modifier.height(20.dp))

                LoadStateAnimatedContent(
                    modifier = Modifier.fillMaxWidth(),
                    loadState = districts,
                    onSuccess = { DistrictCarousel(it, onDistrictClicked) },
                    onLoading = { DistrictCarouselShimmer() }
                )

                nearbyRecommendedPlacesQuery?.run {
                    PlacesCardHeaderCarousel(
                        "주변 인기 장소",
                        result,
                        onPlaceClicked,
                        onMoreClicked = { onMorePlaceListClicked(queryParams) }
                    )
                }

                recommendedPlacesQuery?.run {
                    PlacesCardHeaderCarousel(
                        "추천 인기 장소",
                        result,
                        onPlaceClicked, onMoreClicked = { onMorePlaceListClicked(queryParams) }
                    )
                }

                if (showLocationPermissionBanner) {
                    MainHomeLocationPermissionBanner(
                        Modifier.fillMaxWidth(),
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

                recommendedCoursesQuery?.run {
                    CourseCardHeaderCarousel(
                        "추천 코스",
                        result,
                        onCourseClicked,
                        onMoreClicked = { onMoreCourseListClicked(queryParams) }
                    )
                }

                LoadStateAnimatedContent(
                    loadState = featured,
                    onSuccess = {
                        FeaturedList(it, onFeaturedClicked, onMoreFeaturedListClicked)
                    }
                )

            }

            Spacer(modifier = Modifier.navigationBarsPadding())
            Spacer(modifier = Modifier.height(100.dp))

        }


    }
}

@Composable
fun FeaturedList(
    featuredList: List<Featured>,
    onFeaturedClicked: (Featured) -> Unit,
    onMoreFeaturedClicked: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.height(20.dp))

        Header("읽을 거리", "더보기", onMoreButtonClicked = onMoreFeaturedClicked)

        val windowSize = rememberWindowSizeClass()
        val isMedium = windowSize == WindowSize.Medium

        val column = when (windowSize) {
            WindowSize.Expanded -> 3
            WindowSize.Medium -> 2
            else -> 1
        }

        featuredList.chunked(column).forEach {
            Row(modifier = Modifier.run { if (!isMedium) padding(horizontal = 30.dp) else this }) {
                it.forEachIndexed { index, featured ->
                    if (index == 0) Spacer(modifier = Modifier.width(20.dp))
                    FeaturedListItem(
                        modifier = Modifier.weight(1f).run {
                            if (!isMedium) height(350.dp) else height(400.dp)
                        },
                        featured = featured,
                        onFeaturedClicked = onFeaturedClicked
                    )
                    Spacer(modifier = Modifier.width(if (index == it.lastIndex) 20.dp else 20.dp))
                }

                if (it.size < column) {
                    repeat(column - it.size) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }

            }
            Spacer(modifier = Modifier.height(20.dp))
        }

        Spacer(modifier = Modifier.height(10.dp))
    }
}