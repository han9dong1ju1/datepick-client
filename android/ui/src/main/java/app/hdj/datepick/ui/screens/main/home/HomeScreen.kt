package app.hdj.datepick.ui.screens.main.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import app.hdj.datepick.ui.components.Button
import app.hdj.datepick.ui.components.DatePickScaffold
import app.hdj.datepick.ui.navigation.NavigationGraph
import app.hdj.datepick.ui.providers.LocalParentNavController
import app.hdj.datepick.ui.providers.ProvideBasicsForPreview
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
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            text = "Hello"
        ) {
            navController.navigate(NavigationGraph.Settings.route)
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