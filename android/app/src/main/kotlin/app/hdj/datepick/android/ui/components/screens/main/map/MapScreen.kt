package app.hdj.datepick.android.ui.components.screens.main.map

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.ui.components.DatePickGoogleMap
import app.hdj.datepick.ui.components.rememberGoogleMapState
import app.hdj.datepick.ui.styles.DatePickTheme
import app.hdj.datepick.ui.utils.extract
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun MapScreen(vm: MapViewModelDelegate = hiltViewModel<MapViewModel>()) {

    val (state, effect, event) = vm.extract()

    val googleMapState = rememberGoogleMapState()

    DatePickGoogleMap(
        modifier = Modifier.fillMaxSize(),
        googleMapState = googleMapState,
        onMarkerClicked = {

        }
    )

}

@Composable
@Preview
fun MapScreenPreview() {
    DatePickTheme {
        MapScreen(fakeMapViewModel())
    }
}