package app.hdj.datepick.ui.screens.others.settings

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.ui.styles.DatePickTheme
import app.hdj.datepick.ui.utils.extract

@Composable
fun SettingsScreen(vm: SettingsViewModelDelegate = hiltViewModel<SettingsViewModel>()) {

    val (state, effect, event) = vm.extract()


}

@Composable
@Preview
fun SettingsScreenPreview() {
    DatePickTheme {
        SettingsScreen(fakeSettingsViewModel())
    }
}