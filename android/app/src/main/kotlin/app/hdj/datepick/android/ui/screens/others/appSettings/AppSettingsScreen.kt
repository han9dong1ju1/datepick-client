package app.hdj.datepick.android.ui.screens.others.appSettings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.dialog
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import app.hdj.datepick.android.ui.screens.AppNavigationGraph
import app.hdj.datepick.android.ui.screens.AppNavigationGraph.AppSettings.*
import app.hdj.datepick.android.ui.screens.appNavigationComposable
import app.hdj.datepick.android.ui.screens.others.appSettings.AppSettingsViewModelDelegate.Event.UpdateTheme
import app.hdj.datepick.android.ui.screens.others.appSettings.dialog.AppThemeDialog
import app.hdj.datepick.android.ui.screens.others.appSettings.settingList.SettingListScreen
import app.hdj.datepick.domain.model.settings.AppTheme
import app.hdj.datepick.ui.animation.materialTransitionZaxisIn
import app.hdj.datepick.ui.animation.materialTransitionZaxisOut
import app.hdj.datepick.ui.components.*
import app.hdj.datepick.ui.utils.extract
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator

@OptIn(ExperimentalMaterialNavigationApi::class)
@Composable
fun AppSettingsScreen(
    vm: AppSettingsViewModelDelegate = hiltViewModel<AppSettingsViewModel>()
) {

    val (state, effect, event) = vm.extract()

    val appNavController = LocalAppNavController.current
    val bottomSheetNavigator = rememberBottomSheetNavigator()
    val settingsNavController = rememberAnimatedNavController(bottomSheetNavigator)

    val navBackStackEntry by settingsNavController.currentBackStackEntryAsState()

    val currentRoute = remember(navBackStackEntry?.destination?.route) {
        navBackStackEntry?.destination?.route
    }

    ModalBottomSheetLayout(
        bottomSheetNavigator = bottomSheetNavigator,
        sheetBackgroundColor = Color.Unspecified,
        scrimColor = Color.Black.copy(0.5f)
    ) {
        AnimatedNavHost(
            modifier = Modifier
                .fillMaxSize(),
            navController = settingsNavController,
            startDestination = SettingList.route,
            enterTransition = { materialTransitionZaxisIn },
            exitTransition = { materialTransitionZaxisOut }
        ) {
            appNavigationComposable(SettingList) {
                SettingListScreen(vm, settingsNavController)
            }

            dialog(ThemeDialog.route) {
                AppThemeDialog(vm, settingsNavController)
            }
        }
    }

}