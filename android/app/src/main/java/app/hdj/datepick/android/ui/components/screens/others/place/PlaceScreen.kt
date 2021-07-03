package app.hdj.datepick.android.ui.components.screens.others.place

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import app.hdj.datepick.android.ui.components.screens.AppNavigationGraph
import app.hdj.datepick.ui.styles.DatePickTheme
import app.hdj.datepick.ui.utils.extract

@Composable
fun PlaceScreen(
    placeId : String? = null,
    vm: PlaceViewModelDelegate = hiltViewModel<PlaceViewModel>()
) {

    val (state, effect, event) = vm.extract()


}

@Composable
@Preview
fun PlaceScreenPreview() {
    DatePickTheme {
        PlaceScreen(vm = fakePlaceViewModel())
    }
}