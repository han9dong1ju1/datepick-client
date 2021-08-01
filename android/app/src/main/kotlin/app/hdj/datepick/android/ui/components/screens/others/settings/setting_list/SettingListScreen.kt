package app.hdj.datepick.android.ui.components.screens.others.settings.setting_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.client.MR
import app.hdj.datepick.android.ui.providers.ProvideBasicsForPreview
import app.hdj.datepick.ui.components.DatePickScaffold
import app.hdj.datepick.ui.components.DatePickTopAppBar
import app.hdj.datepick.ui.components.TopAppBarBackButton
import app.hdj.datepick.ui.styles.DatePickTheme
import app.hdj.datepick.ui.utils.extract
import app.hdj.shared.client.utils.string

@Composable
fun SettingListScreen(
    vm: SettingsViewModelDelegate = hiltViewModel<SettingListViewModel>()
) {

    val (state, effect, event) = vm.extract()

    DatePickScaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            DatePickTopAppBar(
                title = { Text(text = MR.strings.settings_title.string()) },
                navigationIcon = { TopAppBarBackButton() }
            )
        }
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = it.calculateTopPadding())
        ) {



        }

    }

}

@Composable
@Preview
fun SettingsScreenPreview() {
    DatePickTheme {
        ProvideBasicsForPreview {
            SettingListScreen(fakeSettingListViewModel())
        }
    }
}