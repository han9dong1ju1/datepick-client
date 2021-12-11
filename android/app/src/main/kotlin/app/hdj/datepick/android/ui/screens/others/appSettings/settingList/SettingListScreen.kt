package app.hdj.datepick.android.ui.screens.others.appSettings.settingList

import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import app.hdj.datepick.android.ui.screens.AppNavigationGraph
import app.hdj.datepick.android.ui.screens.navigateRoute
import app.hdj.datepick.android.ui.screens.others.appSettings.AppSettingsViewModelDelegate
import app.hdj.datepick.domain.model.settings.AppTheme
import app.hdj.datepick.ui.components.BaseScaffold
import app.hdj.datepick.ui.components.InsetLargeTopAppBar
import app.hdj.datepick.ui.components.ListItem
import app.hdj.datepick.ui.components.TopAppBarBackButton
import app.hdj.datepick.ui.utils.extract

@Composable
fun SettingListScreen(
    vm: AppSettingsViewModelDelegate,
    settingNavController: NavController
) {

    val (state, effect, event) = vm.extract()

    val decayAnimationSpec = rememberSplineBasedDecay<Float>()
    val scrollBehavior = remember(decayAnimationSpec) {
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(decayAnimationSpec)
    }

    BaseScaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            InsetLargeTopAppBar(
                navigationIcon = { TopAppBarBackButton() },
                title = {
                  Text(text = "앱 설정")
                },
                scrollBehavior = scrollBehavior
            )
        }
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            ListItem(
                title = "앱 테마",
                subtitle = when (state.appTheme) {
                    AppTheme.Light -> "밝은 테마"
                    AppTheme.Dark -> "어두운 테마"
                    AppTheme.System -> "시스템 설정에 따른 테마"
                }
            ) {
                settingNavController.navigateRoute(AppNavigationGraph.AppSettings.ThemeDialog)
            }

        }

    }
}