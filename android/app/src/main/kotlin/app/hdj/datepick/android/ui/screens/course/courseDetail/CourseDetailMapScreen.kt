package app.hdj.datepick.android.ui.screens.course.courseDetail

import app.hdj.datepick.android.R
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import app.hdj.datepick.ui.components.BaseScaffold
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

internal val SEOUL_LAT_LNG = LatLng(37.56, 126.97)

@Composable
@Destination(
    navGraph = COURSE_SCREEN_NAV_GRAPH
)
fun CourseDetailMapScreen(
    navigator: DestinationsNavigator
) {

    val context = LocalContext.current

    val isLight = MaterialTheme.colors.isLight

    var mapProperties by remember {
        mutableStateOf(
            MapProperties(
                maxZoomPreference = 20f,
                minZoomPreference = 13f,
                mapStyleOptions = if (isLight) null else MapStyleOptions.loadRawResourceStyle(
                    context,
                    app.hdj.datepick.ui.R.raw.google_map_style
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

    BaseScaffold(Modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            properties = mapProperties,
            uiSettings = mapUiSettings,
            cameraPositionState = cameraPositionState
        )
    }
}