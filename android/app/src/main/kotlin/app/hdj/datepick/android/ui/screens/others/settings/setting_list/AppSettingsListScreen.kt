package app.hdj.datepick.android.ui.screens.others.settings.setting_list

import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import app.hdj.datepick.android.ui.providers.PreviewScope
import app.hdj.datepick.android.ui.screens.AppNavigationGraph
import app.hdj.datepick.android.ui.screens.AppNavigationGraph.AppSettings.AppThemeDialog
import app.hdj.datepick.android.ui.screens.navigateRoute
import app.hdj.datepick.ui.components.*
import app.hdj.datepick.ui.styles.BaseTheme
import app.hdj.datepick.ui.utils.extract

@Composable
fun AppSettingsListScreen(
    vm: AppSettingsViewModelDelegate = hiltViewModel<AppSettingsListViewModel>()
) {

    val navController = LocalAppNavController.current

    val (state, effect, event) = vm.extract()

    val decayAnimationSpec = rememberSplineBasedDecay<Float>()
    val scrollBehavior = remember(decayAnimationSpec) {
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(decayAnimationSpec)
    }

    BaseScaffold(
        modifier = Modifier
            .fillMaxWidth()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            InsetLargeTopAppBar(
                title = { Text(text = "앱 설정") },
                navigationIcon = { TopAppBarBackButton() },
                scrollBehavior = scrollBehavior
            )
        }
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            Header(title = "앱 보기 설정")
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