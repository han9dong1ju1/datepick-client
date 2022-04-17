package app.hdj.datepick.android.ui.screens.main.map

import androidx.activity.compose.BackHandler
import androidx.compose.animation.*
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.BottomSheetValue.Collapsed
import androidx.compose.material.BottomSheetValue.Expanded
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Place
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.android.ui.components.SearchBox
import app.hdj.datepick.android.ui.components.SearchBoxExpandedContent
import app.hdj.datepick.android.ui.components.list.itemHorizontalCoursesWithHeader
import app.hdj.datepick.android.ui.components.list.itemHorizontalPlacesWithHeader
import app.hdj.datepick.android.ui.components.rememberSearchBoxState
import app.hdj.datepick.android.ui.destinations.CourseDetailScreenDestination
import app.hdj.datepick.android.ui.destinations.KakaoPlaceSearchScreenDestination
import app.hdj.datepick.android.ui.destinations.PlaceDetailScreenDestination
import app.hdj.datepick.android.ui.destinations.RegionSelectScreenDestination
import app.hdj.datepick.android.ui.providers.preview.FakeCoursePreviewProvider
import app.hdj.datepick.android.ui.providers.preview.FakePlacePreviewProvider
import app.hdj.datepick.android.ui.screens.course.courseDetail.SEOUL_LAT_LNG
import app.hdj.datepick.android.utils.extract
import app.hdj.datepick.android.utils.onCourseClicked
import app.hdj.datepick.android.utils.onPlaceClicked
import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.model.course.Course
import app.hdj.datepick.domain.model.place.Place
import app.hdj.datepick.domain.onSucceed
import app.hdj.datepick.presentation.main.MapScreenViewModel
import app.hdj.datepick.presentation.main.MapScreenViewModelDelegate
import app.hdj.datepick.ui.R
import app.hdj.datepick.ui.components.BaseButton
import app.hdj.datepick.ui.components.BaseScaffold
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.ktx.utils.sphericalDistance
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

private val BottomSheetState.currentFraction: Float
    get() {
        val fraction = progress.fraction
        val targetValue = targetValue
        val currentValue = currentValue

        return when {
            currentValue == Collapsed && targetValue == Collapsed -> 0f
            currentValue == Expanded && targetValue == Expanded -> 1f
            currentValue == Collapsed && targetValue == Expanded -> fraction
            else -> 1f - fraction
        }
    }


@Composable
@Destination
fun MapScreen(
    navigator: DestinationsNavigator
) {
    MapScreenContent(
        popBackStack = { navigator.popBackStack() },
        onSelectRegionClicked = { navigator.navigate(RegionSelectScreenDestination) },
        onPlaceClicked = navigator.onPlaceClicked,
        onCourseClicked = navigator.onCourseClicked,
        onKakaoPlaceSearchClicked = {
            navigator.navigate(KakaoPlaceSearchScreenDestination)
        }
    )
}


@Composable
private fun MapScreenContent(
    vm: MapScreenViewModelDelegate = hiltViewModel<MapScreenViewModel>(),
    popBackStack: () -> Unit = {},
    onSelectRegionClicked: () -> Unit = {},
    onPlaceClicked: (Place) -> Unit = {},
    onCourseClicked: (Course) -> Unit = {},
    onKakaoPlaceSearchClicked: () -> Unit = {}
) {

    val (state, event, effect) = vm.extract()

    val context = LocalContext.current
    val isLight = MaterialTheme.colors.isLight

    val searchBoxState = rememberSearchBoxState()

    var mapProperties by remember {
        mutableStateOf(
            MapProperties(
                maxZoomPreference = 20f,
                minZoomPreference = 13f,
                mapStyleOptions = if (isLight) null else MapStyleOptions.loadRawResourceStyle(
                    context,
                    R.raw.google_map_style
                )
            )
        )
    }

    var mapUiSettings by remember {
        mutableStateOf(
            MapUiSettings(
                mapToolbarEnabled = false,
                rotationGesturesEnabled = false,
                compassEnabled = false,
                myLocationButtonEnabled = false,
                zoomControlsEnabled = false
            )
        )
    }

    var previousCameraPosition by remember {
        mutableStateOf(
            CameraPosition.fromLatLngZoom(SEOUL_LAT_LNG, 15f)
        )
    }

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(SEOUL_LAT_LNG, 15f)
    }

    val bottomSheetState = rememberBottomSheetState(initialValue = Collapsed)

    val bottomSheetScaffoldState =
        rememberBottomSheetScaffoldState(bottomSheetState = bottomSheetState)

    val coroutineScope = rememberCoroutineScope()

    BackHandler {
        when {
            bottomSheetState.isExpanded -> coroutineScope.launch { bottomSheetState.collapse() }
            searchBoxState.isExpanded -> searchBoxState.collapse()
            else -> popBackStack()
        }
    }

    BaseScaffold(modifier = Modifier.fillMaxSize()) {

        val roundedCornerRadius by animateDpAsState(if (bottomSheetState.isExpanded) 0.dp else 20.dp)

        BottomSheetScaffold(
            scaffoldState = bottomSheetScaffoldState,
            sheetBackgroundColor = MaterialTheme.colors.surface,
            sheetPeekHeight = 300.dp,
            sheetShape = RoundedCornerShape(topStart = roundedCornerRadius, topEnd = roundedCornerRadius),
            sheetElevation = 0.dp,
            sheetContent = {
                MapScreenListContent(
                    onPlaceClicked = onPlaceClicked,
                    onCourseClicked = onCourseClicked,
                    onKakaoPlaceSearchClicked = onKakaoPlaceSearchClicked
                )
            }
        ) {
            BaseScaffold(Modifier.fillMaxSize()) {
                GoogleMap(
                    modifier = Modifier.fillMaxSize(),
                    properties = mapProperties,
                    uiSettings = mapUiSettings,
                    cameraPositionState = cameraPositionState
                )
            }
        }

        Box(modifier = Modifier.fillMaxWidth()) {

            val statusBarScrimStartAlpha by animateFloatAsState(
                targetValue = if (searchBoxState.isExpanded) 1f else {
                    if (MaterialTheme.colors.isLight) 0.7f
                    else 0.5f
                }
            )

            val statusBarScrimEndAlpha by animateFloatAsState(
                targetValue = if (searchBoxState.isExpanded) 1f else 0f
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(WindowInsets.statusBars.getBottom(LocalDensity.current).dp)
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                MaterialTheme.colors.surface.copy(alpha = statusBarScrimStartAlpha),
                                MaterialTheme.colors.surface.copy(alpha = statusBarScrimEndAlpha)
                            )
                        )
                    )
            )

            Column(modifier = Modifier.align(Alignment.TopCenter)) {
                Spacer(Modifier.statusBarsPadding())
                Spacer(Modifier.height(80.dp))
                BaseButton(
                    modifier = Modifier,
                    text = "이 장소에서 검색",
                    icon = Icons.Rounded.Place,
                    onClick = {
                        previousCameraPosition = cameraPositionState.position
                    },
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.surface
                    )
                )
            }

            SearchBox(state = searchBoxState) {
                SearchBoxExpandedContent(
                    onCollapse = { searchBoxState.collapse() },
                    onSearch = {
                        coroutineScope.launch {
                            bottomSheetState.collapse()
                            searchBoxState.collapse()
                        }
                    },
                    onSelectRegionClicked = onSelectRegionClicked
                )
            }
        }
    }

}

@Composable
private fun MapScreenListContent(
    popularPlacesState: LoadState<List<Place>> = remember { LoadState.success(FakePlacePreviewProvider().values.first()) },
    popularCoursesState: LoadState<List<Course>> = remember { LoadState.success(FakeCoursePreviewProvider().values.first()) },
    onPlaceClicked: (Place) -> Unit,
    onCourseClicked: (Course) -> Unit,
    onKakaoPlaceSearchClicked: () -> Unit
) {

    LazyColumn(modifier = Modifier.fillMaxSize()) {

        stickyHeader {
            Spacer(Modifier.statusBarsPadding())
            Surface(modifier = Modifier.fillMaxWidth()) {
                Box(modifier = Modifier.padding(20.dp)) {
                    Text("인기", style = MaterialTheme.typography.h5)
                }
            }
        }

        popularPlacesState.onSucceed {
            itemHorizontalPlacesWithHeader("인기 장소", it, onPlaceClicked, onMoreClicked = {})
        }

        popularCoursesState.onSucceed {
            itemHorizontalCoursesWithHeader("인기 코스", it, onCourseClicked, onMoreClicked = {})
        }

        item {
            Spacer(Modifier.height(30.dp))
            BaseButton(
                modifier = Modifier,
                text = "찾는 장소가 없나요?",
                onClick = onKakaoPlaceSearchClicked
            )
        }

        item {
            Spacer(modifier = Modifier.height(100.dp))
            Spacer(modifier = Modifier.navigationBarsPadding())
        }

    }
}