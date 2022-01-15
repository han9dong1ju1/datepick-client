package app.hdj.datepick.android.ui.screens.main.map

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.BottomSheetValue.Collapsed
import androidx.compose.material.BottomSheetValue.Expanded
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.android.ui.components.SearchBox
import app.hdj.datepick.android.ui.components.rememberSearchBoxState
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import app.hdj.datepick.ui.components.BaseScaffold
import app.hdj.datepick.ui.components.googlemap.GoogleMap
import app.hdj.datepick.ui.components.googlemap.rememberCameraUpdateState
import app.hdj.datepick.ui.components.googlemap.rememberMarkerOptionsState
import app.hdj.datepick.ui.components.googlemap.rememberPolylineOptionsState
import app.hdj.datepick.ui.utils.extract
import com.google.accompanist.insets.statusBarsHeight
import com.google.accompanist.insets.statusBarsPadding
import kotlinx.coroutines.launch

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

    val appNavController = LocalAppNavController.current

    val cameraPosition = rememberCameraUpdateState()
    val polylineOptionsState = rememberPolylineOptionsState()
    val markerOptionsState = rememberMarkerOptionsState()

    val bottomSheetState = rememberBottomSheetState(initialValue = Collapsed)

    val bottomSheetScaffoldState =
        rememberBottomSheetScaffoldState(bottomSheetState = bottomSheetState)

    val searchBoxState = rememberSearchBoxState()

    val coroutineScope = rememberCoroutineScope()

    BackHandler {
        when {
            bottomSheetState.isExpanded -> coroutineScope.launch { bottomSheetState.collapse() }
            searchBoxState.isExpanded -> searchBoxState.collapse()
            else -> appNavController.popBackStack()
        }
    }

    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetBackgroundColor = MaterialTheme.colors.surface,
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