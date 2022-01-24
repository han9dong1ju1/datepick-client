package app.hdj.datepick.android.ui.screens.main.home

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.Icon
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
import app.hdj.datepick.android.ui.components.list.FeaturedListItem
import app.hdj.datepick.android.ui.components.list.Header
import app.hdj.datepick.android.ui.components.list.PlaceHorizontalList
import app.hdj.datepick.android.ui.icons.DatePickIcons
import app.hdj.datepick.android.ui.icons.Logo
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import app.hdj.datepick.android.ui.providers.LocalLocationTracker
import app.hdj.datepick.android.ui.providers.preview.FakePlacePreviewProvider
import app.hdj.datepick.android.ui.screens.AppNavigationGraph.LocationPermissionDeniedDialog
import app.hdj.datepick.android.ui.screens.navigateRoute
import app.hdj.datepick.android.utils.extract
import app.hdj.datepick.presentation.main.home.HomeScreenViewModel
import app.hdj.datepick.presentation.main.home.HomeScreenViewModelDelegate
import app.hdj.datepick.presentation.main.home.HomeScreenViewModelDelegate.Event.LocationPermissionResult
import app.hdj.datepick.ui.components.BaseScaffold
import app.hdj.datepick.ui.components.BaseTopBar
import app.hdj.datepick.ui.components.ViewPager
import app.hdj.datepick.ui.utils.getActivity
import app.hdj.datepick.ui.utils.isPermissionGranted
import app.hdj.datepick.utils.PlatformLogger
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun HomeScreen(
    vm: HomeScreenViewModelDelegate = hiltViewModel<HomeScreenViewModel>()
) {

    val (state, effect, event) = vm.extract()

    val context = LocalContext.current
    val navController = LocalAppNavController.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val locationTracker = LocalLocationTracker.current

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

    LaunchedEffect(state.showNearbyRecommendations) {
        if (state.showNearbyRecommendations) {
            val currentLocation = locationTracker.getCurrentLocation()
            if (currentLocation != null) event(HomeScreenViewModelDelegate.Event.CurrentLocation(currentLocation))
        }
    }

    BaseScaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            BaseTopBar(
                title = {
                    Icon(DatePickIcons.Logo, null, modifier = Modifier.height(18.dp))
                }
            )
        }
    ) {

        LazyColumn(modifier = Modifier.padding(it)) {

            item {
                Spacer(modifier = Modifier.height(20.dp))
                ViewPager(
                    Modifier.fillMaxWidth(),
                    state.featuredList,
                    itemSpacing = 10.dp,
                    contentPadding = PaddingValues(horizontal = 20.dp),
                    autoScrollDelay = 5000L
                ) { item, _ ->
                    FeaturedListItem(featured = item) {

                    }
                }
            }

            if (state.showNearbyRecommendations) {
                mainHomeNearbyRecommendedPlaces()
                mainHomePopularPlaces()
            } else {
                mainHomePopularPlaces()
                if (state.showNearbyRecommendLocationPermissionBanner) {
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

                                if (showDeniedDialog) {
                                    navController.navigateRoute(LocationPermissionDeniedDialog)
                                } else {
                                    locationPermissionRequest.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                                }
                            },
                            { event(HomeScreenViewModelDelegate.Event.IgnoreNearbyRecommend) },
                        )
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(20.dp)) }

        }

    }

}

fun LazyListScope.mainHomePopularPlaces() {
    item {
        Spacer(modifier = Modifier.height(30.dp))
        Header("전체 인기장소", "더보기") {

        }
        PlaceHorizontalList(FakePlacePreviewProvider().values.first()) {

        }
    }

}

fun LazyListScope.mainHomeNearbyRecommendedPlaces() {
    item {
        Spacer(modifier = Modifier.height(30.dp))
        Header("주변 추천 인기장소", "더보기") {

        }
        PlaceHorizontalList(FakePlacePreviewProvider().values.first()) {

        }
    }

}