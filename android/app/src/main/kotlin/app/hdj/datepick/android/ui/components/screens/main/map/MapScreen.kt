package app.hdj.datepick.android.ui.components.screens.main.map

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.ui.components.DatePickGoogleMap
import app.hdj.datepick.ui.components.rememberGoogleMapState
import app.hdj.datepick.ui.styles.DatePickTheme
import app.hdj.datepick.ui.utils.extract

@Composable
fun MapScreen(vm: MapViewModelDelegate = hiltViewModel<MapViewModel>()) {

    val (state, effect, event) = vm.extract()

    val googleMapState = rememberGoogleMapState()


    DatePickGoogleMap(
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