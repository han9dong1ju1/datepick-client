package app.hdj.datepick.android.ui.screens.main.map

import androidx.activity.compose.BackHandler
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.BottomSheetValue.Collapsed
import androidx.compose.material.BottomSheetValue.Expanded
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Place
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.android.ui.components.googlemap.PlaceGoogleMapMarker
import app.hdj.datepick.android.ui.components.list.PlacesCardGridWithHeaderWithQuery
import app.hdj.datepick.android.ui.components.searchbox.SearchBox
import app.hdj.datepick.android.ui.destinations.CheckboxSelectDialogDestination
import app.hdj.datepick.android.ui.destinations.KakaoPlaceSearchScreenDestination
import app.hdj.datepick.android.ui.destinations.RegionSelectScreenDestination
import app.hdj.datepick.android.ui.dialog.CheckboxSelectDialogConfig
import app.hdj.datepick.android.ui.dialog.CheckboxSelectDialogResult
import app.hdj.datepick.android.ui.screens.course.courseDetail.SEOUL_LAT_LNG
import app.hdj.datepick.android.utils.*
import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.model.course.Course
import app.hdj.datepick.domain.model.place.Place
import app.hdj.datepick.domain.usecase.place.params.PlaceQueryParams
import app.hdj.datepick.domain.usecase.place.params.PlaceQueryWithResult
import app.hdj.datepick.presentation.main.MapScreenViewModel
import app.hdj.datepick.presentation.main.MapScreenViewModelDelegate
import app.hdj.datepick.presentation.main.MapScreenViewModelDelegate.Event.PlaceQueryChanged
import app.hdj.datepick.ui.R
import app.hdj.datepick.ui.components.BaseButton
import app.hdj.datepick.ui.components.BaseScaffold
import app.hdj.datepick.utils.location.LatLng
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.*
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.result.NavResult
import com.ramcosta.composedestinations.result.ResultRecipient
import kotlinx.coroutines.launch

@Composable
@Destination
fun MapScreen(
    navigator: DestinationsNavigator,
    resultRecipient: ResultRecipient<CheckboxSelectDialogDestination, CheckboxSelectDialogResult>
) {

    val vm = hiltViewModel<MapScreenViewModel>()

    val (state) = vm.extract()

    resultRecipient.onNavResult {
        if (it is NavResult.Value) {
            vm.event(
                PlaceQueryChanged(
                    state.searchedPlacesResult.queryParams.run {
                        copy(filterParams = filterParams.copy(categoryIds = it.value.selected))
                    }
                )
            )
        }
    }

    MapScreenContent(
        vm = vm,
        popBackStack = { navigator.popBackStack() },
        onSelectRegionClicked = { navigator.navigate(RegionSelectScreenDestination) },
        onPlaceClicked = navigator.onPlaceClicked,
        onMorePlacesClicked = navigator.onMorePlaceListClicked,
        onCourseClicked = navigator.onCourseClicked,
        onKakaoPlaceSearchClicked = { navigator.navigate(KakaoPlaceSearchScreenDestination) },
        onCategoryRemove = { category ->
            state.searchedPlacesResult.queryParams.run {
                val previousCategories = filterParams.categoryIds
                if (!previousCategories.isNullOrEmpty()) {
                    vm.event(
                        PlaceQueryChanged(
                            copy(
                                filterParams = filterParams.copy(
                                    categoryIds = previousCategories - category.id
                                )
                            )
                        )
                    )
                }
            }
        },
        onCategoryChangeClicked = {
            navigator.navigate(CheckboxSelectDialogDestination(CheckboxSelectDialogConfig(
                "카테고리 선택",
                "카테고리를 선택해주세요.",
                state.placeCategoriesState.dataOrNull?.map { it.id to it.name }
                    .orEmpty(),
                it,
            )))
        },
    )
}


@Composable
private fun MapScreenContent(
    vm: MapScreenViewModelDelegate,
    popBackStack: () -> Unit = {},
    onSelectRegionClicked: () -> Unit = {},
    onPlaceClicked: (Place) -> Unit = {},
    onMorePlacesClicked: (PlaceQueryParams) -> Unit = {},
    onCourseClicked: (Course) -> Unit = {},
    onCategoryRemove: (Place.Category) -> Unit = {},
    onCategoryChangeClicked: (List<Long>) -> Unit = {},
    onKakaoPlaceSearchClicked: () -> Unit = {}
) {

    val (state, effect, event) = vm.extract()

    val context = LocalContext.current
    val isLight = MaterialTheme.colors.isLight

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

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(SEOUL_LAT_LNG, 15f)
    }

    LaunchedEffect(cameraPositionState.position) {
        event(MapScreenViewModelDelegate.Event.ThisLocationSearchClicked(
            LatLng(
                cameraPositionState.position.target.latitude,
                cameraPositionState.position.target.longitude
            )
        ))
    }

    val bottomSheetState = rememberBottomSheetState(initialValue = Collapsed)

    val bottomSheetScaffoldState =
        rememberBottomSheetScaffoldState(bottomSheetState = bottomSheetState)

    val coroutineScope = rememberCoroutineScope()

    var selectedLocation by remember { mutableStateOf<LatLng?>(null) }

    BackHandler {
        when {
            bottomSheetState.isExpanded -> coroutineScope.launch { bottomSheetState.collapse() }
            else -> {
                if (selectedLocation != null) {
                    selectedLocation = null
                } else {
                    popBackStack()
                }
            }
        }
    }


    BaseScaffold(modifier = Modifier.fillMaxSize()) {

        CompositionLocalProvider(LocalElevationOverlay provides null) {

            val screenHeight = LocalConfiguration.current.screenHeightDp.dp

            BottomSheetScaffold(
                scaffoldState = bottomSheetScaffoldState,
                sheetBackgroundColor = MaterialTheme.colors.surface,
                sheetPeekHeight = if (selectedLocation == null) 0.dp else screenHeight * 0.4f,
                sheetShape = RoundedCornerShape(
                    topStart = 30.dp,
                    topEnd = 30.dp
                ),
                sheetElevation = 10.dp,
                sheetContent = {
                    MapScreenListContent(
                        searchedPlacesResult = state.searchedPlacesResult,
                        categoriesState = state.placeCategoriesState,
                        onPlaceQueryParamsChanged = {
                            event(PlaceQueryChanged(it))
                        },
                        onPlaceClicked = onPlaceClicked,
                        onCourseClicked = onCourseClicked,
                        onKakaoPlaceSearchClicked = onKakaoPlaceSearchClicked,
                        onMorePlacesClicked = { onMorePlacesClicked(state.searchedPlacesResult.queryParams) },
                        onCategoryRemove = onCategoryRemove,
                        onCategoryChangeClicked = onCategoryChangeClicked,
                        onSearchBoxFocused = { coroutineScope.launch { bottomSheetState.expand() } }
                    )
                }
            ) {
                BaseScaffold(
                    Modifier.fillMaxSize()
                ) {

                    Box(modifier = Modifier.padding(it)) {

                        GoogleMap(
                            modifier = Modifier.fillMaxSize(),
                            properties = mapProperties,
                            uiSettings = mapUiSettings,
                            cameraPositionState = cameraPositionState
                        ) {
                            state.searchedPlacesResult.result.onSucceedComposable { places ->
                                places.forEach { place ->
                                    PlaceGoogleMapMarker(
                                        markerState = rememberMarkerState(
                                            key = place.id.toString(),
                                            position = com.google.android.gms.maps.model.LatLng(
                                                place.latitude,
                                                place.longitude
                                            )
                                        ),
                                        place = place,
                                        onMarkerClicked = onPlaceClicked
                                    )
                                }
                            }
                        }

                        AnimatedVisibility(
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .padding(bottom = 200.dp),
                            visible = selectedLocation == null,
                            enter = slideInVertically { it },
                            exit = slideOutVertically { it }
                        ) {
                            Button(onClick = {
                                selectedLocation = LatLng(
                                    cameraPositionState.position.target.latitude,
                                    cameraPositionState.position.target.longitude
                                )
                            }) {
                                Text("테스트")
                            }
                        }

                        Column {
                            Spacer(modifier = Modifier.height(20.dp))
                            Spacer(modifier = Modifier.statusBarsPadding())
                            AnimatedVisibility(
                                modifier = Modifier,
                                visible = selectedLocation != null,
                                enter = scaleIn(),
                                exit = scaleOut()
                            ) {
                                Box(modifier = Modifier.fillMaxWidth()) {
                                    BaseButton(
                                        modifier = Modifier.align(Alignment.Center),
                                        text = "이 장소에서 검색",
                                        icon = Icons.Rounded.Place,
                                        elevation = 4.dp,
                                        onClick = {
                                            event(
                                                MapScreenViewModelDelegate.Event.ThisLocationSearchClicked(
                                                    LatLng(
                                                        cameraPositionState.position.target.latitude,
                                                        cameraPositionState.position.target.longitude,
                                                    )
                                                )
                                            )
                                        },
                                        shape = RoundedCornerShape(20.dp),
                                        colors = ButtonDefaults.buttonColors(
                                            backgroundColor = MaterialTheme.colors.surface
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            }

        }

    }
}

@Composable
private fun MapScreenListContent(
    searchedPlacesResult: PlaceQueryWithResult,
    categoriesState: LoadState<List<Place.Category>>,
    onPlaceQueryParamsChanged: (PlaceQueryParams) -> Unit,
    onPlaceClicked: (Place) -> Unit,
    onMorePlacesClicked: () -> Unit,
    onCourseClicked: (Course) -> Unit,
    onCategoryRemove: (Place.Category) -> Unit,
    onCategoryChangeClicked: (List<Long>) -> Unit,
    onKakaoPlaceSearchClicked: () -> Unit,
    onSearchBoxFocused: () -> Unit
) {

    val configuration = LocalConfiguration.current

    val screenHeight = configuration.screenHeightDp.dp

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(screenHeight)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.surface)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Box(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .height(4.dp)
                        .width(50.dp)
                        .background(
                            MaterialTheme.colors.onSurface.copy(alpha = 0.2f),
                            shape = RoundedCornerShape(100.dp)
                        )
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            SearchBox(
                onFocused = onSearchBoxFocused,
                onSearch = {

                }
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {

            PlacesCardGridWithHeaderWithQuery(
                "검색된 장소",
                categoriesState,
                searchedPlacesResult.result,
                onPlaceClicked,
                onMoreClicked = onMorePlacesClicked,
                searchedPlacesResult.queryParams,
                onPlaceQueryParamsChanged,
                onCategoryRemove,
                onCategoryChangeClicked
            )

            Spacer(Modifier.height(30.dp))

            BaseButton(
                modifier = Modifier,
                text = "찾는 장소가 없나요?",
                onClick = onKakaoPlaceSearchClicked
            )

            Spacer(modifier = Modifier.height(100.dp))
            Spacer(modifier = Modifier.navigationBarsPadding())

        }
    }

}