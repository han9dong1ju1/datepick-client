package app.hdj.datepick.android.ui.screens.others.settings

import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.navigation
import app.hdj.datepick.android.ui.screens.AppNavigationGraph
import app.hdj.datepick.android.ui.screens.AppNavigationGraph.Settings.GeneralSettings
import app.hdj.datepick.android.ui.screens.appNavigationComposable
import app.hdj.datepick.android.ui.screens.others.settings.setting_list.SettingListScreen


fun NavGraphBuilder.settingsScreens() {

    navigation(
        startDestination = GeneralSettings.route,
        route = AppNavigationGraph.Settings.route
    ) {

        appNavigationComposable(GeneralSettings) {
            SettingListScreen()
        }

    }

}
