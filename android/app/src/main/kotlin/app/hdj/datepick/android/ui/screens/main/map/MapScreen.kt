package app.hdj.datepick.android.ui.screens.main.map

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.BottomSheetValue.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import app.hdj.datepick.android.ui.components.SearchBox
import app.hdj.datepick.android.ui.components.rememberSearchBoxState
import app.hdj.datepick.ui.components.BaseScaffold
import app.hdj.datepick.ui.components.googlemap.*
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
    MapScreenContent(popBackStack = {
        navigator.popBackStack()
    })
}


@Composable
private fun MapScreenContent(
    popBackStack: () -> Unit = {}
) {

    val searchBoxState = rememberSearchBoxState()

    val uiSettingsState: MapUiSettingsState = rememberMapUiSettings()
    val cameraUpdateState: CameraUpdateState = rememberCameraUpdateState()
    val markerOptionsState: MarkerOptionsState = rememberMarkerOptionsState()
    val polylineOptionsState: PolylineOptionsState = rememberPolylineOptionsState()

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

        BottomSheetScaffold(
            scaffoldState = bottomSheetScaffoldState,
            sheetBackgroundColor = MaterialTheme.colors.surface,
            sheetPeekHeight = 300.dp,
            sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
            sheetElevation = 0.dp,
            sheetContent = {
                LazyColumn {
                    items(1000) {
                        Text(text = it.toString(), modifier = Modifier.padding(20.dp))
                    }
                }
            }
        ) {
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

}