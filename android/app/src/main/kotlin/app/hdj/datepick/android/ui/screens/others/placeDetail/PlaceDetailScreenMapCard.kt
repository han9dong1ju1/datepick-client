package app.hdj.datepick.android.ui.screens.others.placeDetail

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Map
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import app.hdj.datepick.android.ui.screens.openPlace
import app.hdj.datepick.android.ui.screens.openWebUrl
import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.isStateSucceed
import app.hdj.datepick.domain.model.place.Place
import app.hdj.datepick.ui.components.DatePickButton
import app.hdj.datepick.ui.components.googlemap.GoogleMap
import app.hdj.datepick.ui.components.googlemap.rememberCameraUpdateState
import app.hdj.datepick.ui.components.googlemap.rememberMapUiSettings
import app.hdj.datepick.ui.components.googlemap.rememberMarkerOptionsState
import app.hdj.datepick.ui.styles.shapes
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.ktx.model.markerOptions

@Composable
fun PlaceDetailMapCard(placeState: LoadState<Place>) {

    val navController = LocalAppNavController.current

    val uiSettingsState = rememberMapUiSettings {
        setAllGesturesEnabled(false)
        isCompassEnabled = false
        isMapToolbarEnabled = false
        isMyLocationButtonEnabled = false
    }

    val cameraUpdateState = rememberCameraUpdateState()
    val markerOptionsState = rememberMarkerOptionsState()

    LaunchedEffect(placeState) {
        if (placeState.isStateSucceed()) {

            val placeLatLng =
                LatLng(placeState.data.latitude, placeState.data.longitude)

            cameraUpdateState.move(placeLatLng, 15f)

            markerOptionsState.addMarkerOptions(
                listOf(
                    markerOptions {
                        title(placeState.data.name)
                        position(placeLatLng)
                    }
                )
            )
        }
    }

    Surface(
        shape = shapes.medium,
        elevation = 0.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .height(200.dp)
    ) {

        Box(modifier = Modifier.fillMaxSize()) {

            GoogleMap(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                uiSettingsState = uiSettingsState,
                cameraUpdateState = cameraUpdateState,
                markerOptionsState = markerOptionsState,
            )

            DatePickButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(20.dp),
                icon = Icons.Rounded.Map,
                text = "카카오맵으로 보기"
            ) {
                if (placeState.isStateSucceed()) {
                    val url = "https://place.map.kakao.com/${placeState.data.kakaoId}"
                    navController.openWebUrl(url)
                }
            }

        }

    }
}