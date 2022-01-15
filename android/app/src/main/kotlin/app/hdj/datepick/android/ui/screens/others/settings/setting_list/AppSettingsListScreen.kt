package app.hdj.datepick.android.ui.screens.others.settings.setting_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import app.hdj.datepick.android.ui.providers.PreviewScope
import app.hdj.datepick.android.ui.screens.AppNavigationGraph.AppSettings.AppThemeDialog
import app.hdj.datepick.android.ui.screens.navigateRoute
import app.hdj.datepick.ui.components.BaseScaffold
import app.hdj.datepick.ui.components.ListHeader
import app.hdj.datepick.ui.components.ListItem
import app.hdj.datepick.ui.components.TopAppBarBackButton
import app.hdj.datepick.ui.styles.BaseTheme
import app.hdj.datepick.ui.utils.extract

@Composable
fun AppSettingsListScreen(
    vm: AppSettingsViewModelDelegate = hiltViewModel<AppSettingsViewModel>()
) {

    val navController = LocalAppNavController.current

    val (state, effect, event) = vm.extract()

    BaseScaffold(
        modifier = Modifier
            .fillMaxWidth(),
        topBar = {
            com.google.accompanist.insets.ui.TopAppBar(
                title = { Text(text = "앱 설정") },
                navigationIcon = { TopAppBarBackButton() }
            )
        }
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            ListHeader(title = "앱 보기 설정")
            ListItem(title = "앱 테마", subtitle = "앱의 테마를 설정합니다.") {
                navController.navigateRoute(AppThemeDialog)
            }

        }

    }

}

@Composable
@Preview
fun SettingsScreenPreview() {
    BaseTheme {
        PreviewScope {
            AppSettingsListScreen(fakeSettingListViewModel())
        }
    }
}