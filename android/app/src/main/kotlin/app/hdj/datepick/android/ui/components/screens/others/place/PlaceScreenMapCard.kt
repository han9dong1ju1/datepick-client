package app.hdj.datepick.android.ui.components.screens.others.place

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.isStateSucceed
import app.hdj.datepick.domain.model.place.Place
import app.hdj.datepick.ui.components.googlemap.GoogleMap
import app.hdj.datepick.ui.components.googlemap.rememberCameraUpdateState
import app.hdj.datepick.ui.components.googlemap.rememberMapUiSettings
import app.hdj.datepick.ui.components.googlemap.rememberMarkerOptionsState
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.ktx.model.markerOptions

@Composable
fun PlaceMapCard(placeState: LoadState<Place>) {

    val uiSettingsState = rememberMapUiSettings()
    val cameraUpdateState = rememberCameraUpdateState()
    val markerOptionsState = rememberMarkerOptionsState()

    uiSettingsState.uiSettings {
        setAllGesturesEnabled(false)
    }

    Card(
        elevation = 0.dp,
        onClick = {

        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .height(200.dp)
    ) {
        if (placeState.isStateSucceed()) {

            val placeLatLng =
                LatLng(placeState.data.latitude, placeState.data.longitude)

            cameraUpdateState.move(placeLatLng, 15f)

            markerOptionsState.addMarkerOptions(
                listOf(
                    markerOptions {
                        position(placeLatLng)
                        title(placeState.data.name)
                    }
                )
            )

            GoogleMap(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                uiSettingsState = uiSettingsState,
                cameraUpdateState = cameraUpdateState,
                markerOptionsState = markerOptionsState,
            )
        }
    }
}