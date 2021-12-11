package app.hdj.datepick.android.ui.screens.others.appSettings

import androidx.navigation.NavGraphBuilder
import app.hdj.datepick.android.ui.screens.AppNavigationGraph
import app.hdj.datepick.android.ui.screens.appNavigationComposable
import com.google.accompanist.navigation.animation.composable

fun NavGraphBuilder.appSettingsScreen() {

    appNavigationComposable(AppNavigationGraph.AppSettings) {
        AppSettingsScreen()
    }

}
