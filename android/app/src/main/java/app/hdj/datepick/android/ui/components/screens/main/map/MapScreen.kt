package app.hdj.datepick.android.ui.components.screens.main.map

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.ui.components.GoogleMap
import app.hdj.datepick.ui.styles.DatePickTheme
import app.hdj.datepick.ui.utils.extract

@Composable
fun MapScreen(vm: MapViewModelDelegate = hiltViewModel<MapViewModel>()) {

    val (state, effect, event) = vm.extract()

    GoogleMap(latitude = 35.9078, longitude = 127.7669)

}

@Composable
@Preview
fun MapScreenPreview() {
    DatePickTheme {
        MapScreen(fakeMapViewModel())
    }
}