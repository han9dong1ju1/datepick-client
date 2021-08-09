package app.hdj.datepick.android.ui.components.screens.main.pick

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.ui.components.DatePickScaffold
import app.hdj.datepick.ui.styles.DatePickTheme
import app.hdj.datepick.ui.utils.extract

@Composable
fun PickScreen(vm: PickViewModelDelegate = hiltViewModel<PickViewModel>()) {

    val (state, effect, event) = vm.extract()

    DatePickScaffold(modifier = Modifier.fillMaxSize()) {

    }

}

@Composable
@Preview
fun PickScreenPreview() {
    DatePickTheme {
        PickScreen(fakePickViewModel())
    }
}