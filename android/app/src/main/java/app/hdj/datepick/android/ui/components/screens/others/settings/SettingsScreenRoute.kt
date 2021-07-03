package app.hdj.datepick.android.ui.components.screens.others.settings

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import app.hdj.datepick.android.ui.components.screens.AppNavigationGraph
import app.hdj.datepick.android.ui.components.screens.others.settings.setting_list.SettingListScreen


fun NavGraphBuilder.settingsScreens() {

    navigation(
        startDestination = AppNavigationGraph.Settings.List.route,
        route = AppNavigationGraph.Settings.route
    ) {

        composable(AppNavigationGraph.Settings.List.route) {
            SettingListScreen()
        }

    }

}
