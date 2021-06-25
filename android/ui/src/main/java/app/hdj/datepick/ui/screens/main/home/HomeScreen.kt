package app.hdj.datepick.ui.screens.main.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.ui.styles.DatePickTheme
import app.hdj.datepick.ui.utils.extract

@Composable
fun HomeScreen(vm: HomeViewModelDelegate = hiltViewModel<HomeViewModel>()) {

    val (state, effect, event) = vm.extract()

}

@Composable
@Preview
fun HomeScreenPreview() {
    DatePickTheme {
        HomeScreen(fakeHomeViewModel())
    }
}