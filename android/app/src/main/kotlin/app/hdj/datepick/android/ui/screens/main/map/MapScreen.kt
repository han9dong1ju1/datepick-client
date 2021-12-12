package app.hdj.datepick.android.ui.screens.main.map

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.BottomSheetValue.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.android.ui.components.SearchBox
import app.hdj.datepick.android.ui.components.list.PlaceVerticalListItem
import app.hdj.datepick.android.ui.components.rememberSearchBoxState
import app.hdj.datepick.android.ui.providers.preview.FakePlacePreviewProvider
import app.hdj.datepick.ui.components.BaseScaffold
import app.hdj.datepick.ui.components.Header
import app.hdj.datepick.ui.components.googlemap.GoogleMap
import app.hdj.datepick.ui.components.googlemap.rememberCameraUpdateState
import app.hdj.datepick.ui.components.googlemap.rememberMarkerOptionsState
import app.hdj.datepick.ui.components.googlemap.rememberPolylineOptionsState
import app.hdj.datepick.ui.styles.BaseTheme
import app.hdj.datepick.ui.utils.extract
import com.google.accompanist.insets.statusBarsHeight
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalMaterialApi::class)
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
fun MapScreen(vm: MapViewModelDelegate = hiltViewModel<MapViewModel>()) {

    val (state, effect, event) = vm.extract()

    val context = LocalContext.current

    val pages = (0..10).toList()
    val pagerState = rememberPagerState()

    val cameraPosition = rememberCameraUpdateState()
    val polylineOptionsState = rememberPolylineOptionsState()
    val markerOptionsState = rememberMarkerOptionsState()

    val polylineColors = MaterialTheme.colorScheme.primary

    LaunchedEffect(pagerState.currentPage) {

//        event.invoke(
//            MapViewModelDelegate.Event.LoadCoursePlacesPathToMap()
//        )
//
//        val markers = .map { latLng ->
//        markerOptions {
//            icon(polylineColors.getMarkerIcon())
//            position(latLng)
//            title("Example")
//        }
//    }
//
//        cameraPosition.animate(markers.map { it.position }, 100.dp)
//
//        markerOptionsState.clear()
//        markerOptionsState.addMarkerOptions(markers)
//
//        polylineOptionsState.polylineOptions {
//            addAll(markers.map { it.position })
//            startCap(RoundCap())
//            endCap(RoundCap())
//            width(15.dp.value)
//            visible(true)
//            pattern(listOf(Dot(), Gap(15.dp.value)))
//            jointType(JointType.ROUND)
//            color(polylineColors.toArgb())
//        }
    }

    val bottomSheetState = rememberBottomSheetState(initialValue = Collapsed)

    val bottomSheetScaffoldState =
        rememberBottomSheetScaffoldState(bottomSheetState = bottomSheetState)

    val searchBoxState = rememberSearchBoxState()

    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetBackgroundColor = MaterialTheme.colorScheme.surface,
        sheetPeekHeight = 300.dp,
        sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        sheetElevation = 0.dp,
        sheetContent = { MapBottomSheetContent(bottomSheetState) }
    ) {
        BaseScaffold(Modifier.fillMaxSize()) {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                markerOptionsState = markerOptionsState,
                polylineOptionsState = polylineOptionsState,
                cameraUpdateState = cameraPosition
            )
        }


    }

    Box(modifier = Modifier.fillMaxWidth()) {

        val statusBarScrimStartAlpha by animateFloatAsState(
            targetValue = if (searchBoxState.isExpanded) 1f else {
                if (androidx.compose.material.MaterialTheme.colors.isLight) 0.7f
                else 0.5f
            }
        )

        val statusBarScrimEndAlpha by animateFloatAsState(
            targetValue = if (searchBoxState.isExpanded) 1f else 0f
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsHeight()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            MaterialTheme.colorScheme.surface.copy(alpha = statusBarScrimStartAlpha),
                            MaterialTheme.colorScheme.surface.copy(alpha = statusBarScrimEndAlpha)
                        )
                    )
                )
        )

        val fraction = bottomSheetState.currentFraction

        AnimatedVisibility(
            visible = fraction <= 0.4,
            enter = slideInVertically { -it },
            exit = slideOutVertically { -it }
        ) {
            SearchBox(
                modifier = Modifier.statusBarsPadding(),
                state = searchBoxState
            )
        }
    }
}

@Composable
@Preview
fun MapScreenPreview() {
    BaseTheme {
        MapScreen(fakeMapViewModel())
    }
}