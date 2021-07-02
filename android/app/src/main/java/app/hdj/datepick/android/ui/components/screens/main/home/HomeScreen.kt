package app.hdj.datepick.android.ui.components.screens.main.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.ui.components.DatePickButton
import app.hdj.datepick.ui.components.DatePickScaffold
import app.hdj.datepick.android.ui.components.screens.AppNavigationGraph
import app.hdj.datepick.ui.providers.LocalParentNavController
import app.hdj.datepick.ui.styles.DatePickTheme
import app.hdj.datepick.ui.utils.extract

@Composable
fun HomeScreen(
    vm: HomeViewModelDelegate = hiltViewModel<HomeViewModel>()
) {

    val navController = LocalParentNavController.current

    val (state, effect, event) = vm.extract()

    DatePickScaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        DatePickButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            text = "Hello"
        ) {
            navController.navigate(AppNavigationGraph.Settings.route)
        }
    }

}

@Composable
@Preview
fun HomeScreenPreview() {
    DatePickTheme {
        HomeScreen(fakeHomeViewModel())
    }
}