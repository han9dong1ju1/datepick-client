package app.hdj.datepick.android.ui.components.screens.main.map

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.ui.components.DatePickPager
import app.hdj.datepick.ui.components.DatePickScaffold
import app.hdj.datepick.ui.components.DatePickTopAppBar
import app.hdj.datepick.ui.components.googlemap.*
import app.hdj.datepick.ui.styles.DatePickTheme
import app.hdj.datepick.ui.utils.extract
import com.google.accompanist.insets.navigationBarsHeight
import com.google.accompanist.pager.rememberPagerState
import com.google.android.gms.maps.model.JointType
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.RoundCap
import com.google.maps.android.ktx.model.markerOptions
import kotlinx.coroutines.delay
import kotlin.random.Random

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
        delay(1000)

        val latRand = { (5600..5700).random() / 10000.0 }

        val lngRand = { (9600..9800).random() / 10000.0 }

        val markers = (0..4 + Random.nextInt(3)).map {
            LatLng(37.0 + latRand(), 126.0 + lngRand())
        }.shuffled().map { latLng ->
            markerOptions {
                position(latLng)
                title("Example")
            }
        }

        cameraPosition.animate(markers.map { it.position }, 100.dp)

        markerOptionsState.clear()
        markerOptionsState.addMarkerOptions(markers)

        polylineOptionsState.polylineOptions {
            addAll(markers.map { it.position })
            startCap(RoundCap())
            endCap(RoundCap())
            width(10.dp.value)
            visible(true)
            zIndex(30f)
//            pattern(listOf(Do, Gap(10.dp.value)))
            jointType(JointType.ROUND)
            color(polylineColors.toArgb())
        }
    }

    DatePickScaffold(Modifier.fillMaxSize(), topBar = {
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
                DatePickPager(
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
                            .height(4.dp)
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
    DatePickTheme {
        MapScreen(fakeMapViewModel())
    }
}