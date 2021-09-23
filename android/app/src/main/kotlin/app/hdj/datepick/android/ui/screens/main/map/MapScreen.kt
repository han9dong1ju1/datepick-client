package app.hdj.datepick.android.ui.screens.main.map

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.ui.components.ViewPager
import app.hdj.datepick.ui.components.BaseScaffold
import app.hdj.datepick.ui.components.DatePickTopAppBar
import app.hdj.datepick.ui.components.googlemap.*
import app.hdj.datepick.ui.styles.BaseTheme
import app.hdj.datepick.ui.styles.onSurface10
import app.hdj.datepick.ui.utils.extract
import com.google.accompanist.insets.navigationBarsHeight
import com.google.accompanist.pager.rememberPagerState

@Composable
fun MapScreen(vm: MapViewModelDelegate = hiltViewModel<MapViewModel>()) {

    val (state, effect, event) = vm.extract()

    val context = LocalContext.current

    val pages = (0..10).toList()
    val pagerState = rememberPagerState(pageCount = pages.size)

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

    BaseScaffold(Modifier.fillMaxSize(), topBar = {
        DatePickTopAppBar()
    }) {

        Box(modifier = Modifier.fillMaxSize()) {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                markerOptionsState = markerOptionsState,
                polylineOptionsState = polylineOptionsState,
                cameraUpdateState = cameraPosition
            )

            Column(
                Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            ) {
                ViewPager(
                    modifier = Modifier.fillMaxWidth(),
                    list = pages,
                    pagerState = pagerState,
                    itemSpacing = (-30).dp
                ) { _, _ ->

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                            .padding(20.dp)
                    ) {

                    }

                }

                if (state.showProgressBar) {
                    LinearProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(2.dp),
                        backgroundColor = MaterialTheme.colors.onSurface10
                    )
                }

                Spacer(modifier = Modifier.navigationBarsHeight(56.dp))


            }


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