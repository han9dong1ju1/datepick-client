package app.hdj.datepick.android.ui.components.screens.others.settings

import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation
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
