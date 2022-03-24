package app.hdj.datepick.android.ui.screens.course.courseDetail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.hdj.datepick.ui.components.BaseScaffold
import app.hdj.datepick.ui.components.googlemap.*
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination(
    navGraph = COURSE_SCREEN_NAV_GRAPH
)
fun CourseDetailMapScreen(
    navigator: DestinationsNavigator
) {

    val uiSettingsState: MapUiSettingsState = rememberMapUiSettings()
    val cameraUpdateState: CameraUpdateState = rememberCameraUpdateState()
    val markerOptionsState: MarkerOptionsState = rememberMarkerOptionsState()
    val polylineOptionsState: PolylineOptionsState = rememberPolylineOptionsState()

    BaseScaffold(Modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            markerOptionsState = markerOptionsState,
            polylineOptionsState = polylineOptionsState,
            cameraUpdateState = cameraUpdateState,
            uiSettingsState = uiSettingsState
        )
    }
}