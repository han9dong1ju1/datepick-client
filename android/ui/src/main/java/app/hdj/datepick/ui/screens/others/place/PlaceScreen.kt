package app.hdj.datepick.ui.screens.others.place

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.ui.styles.DatePickTheme
import app.hdj.datepick.ui.utils.extract

@Composable
fun PlaceScreen(vm: PlaceViewModelDelegate = hiltViewModel<PlaceViewModel>()) {

    val (state, effect, event) = vm.extract()


}

@Composable
@Preview
fun PlaceScreenPreview() {
    DatePickTheme {
        PlaceScreen(fakePlaceViewModel())
    }
}