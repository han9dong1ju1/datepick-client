package app.hdj.datepick.android.ui.screens.main.map

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.ui.components.BaseScaffold
import app.hdj.datepick.ui.components.googlemap.GoogleMap
import app.hdj.datepick.ui.components.googlemap.rememberCameraUpdateState
import app.hdj.datepick.ui.components.googlemap.rememberMarkerOptionsState
import app.hdj.datepick.ui.components.googlemap.rememberPolylineOptionsState
import app.hdj.datepick.ui.styles.BaseTheme
import app.hdj.datepick.ui.utils.extract
import com.google.accompanist.insets.statusBarsHeight
import com.google.accompanist.pager.rememberPagerState

@Composable
fun MapScreen(vm: MapViewModelDelegate = hiltViewModel<MapViewModel>()) {

    val (state, effect, event) = vm.extract()

    val context = LocalContext.current

    val pages = (0..10).toList()
    val pagerState = rememberPagerState()

    val cameraPosition = rememberCameraUpdateState()
    val polylineOptionsState = rememberPolylineOptionsState()
    val markerOptionsState = rememberMarkerOptionsState()

    val polylineColors = MaterialTheme.colors.primary

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

    BaseScaffold(Modifier.fillMaxSize(),
        topBar = {
            Column(Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.statusBarsHeight().background(MaterialTheme.colors.background.copy(alpha = 0.2f)))
            }
        }
    ) {

        Box(modifier = Modifier.fillMaxSize()) {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                markerOptionsState = markerOptionsState,
                polylineOptionsState = polylineOptionsState,
                cameraUpdateState = cameraPosition
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