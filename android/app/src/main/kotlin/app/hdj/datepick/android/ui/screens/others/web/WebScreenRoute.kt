package app.hdj.datepick.android.ui.screens.others.web

import androidx.navigation.NavGraphBuilder
import app.hdj.datepick.android.ui.screens.AppNavigationGraph
import app.hdj.datepick.android.ui.screens.appNavigationComposable

fun NavGraphBuilder.webScreen() {
    appNavigationComposable(AppNavigationGraph.Web) {
        val url = it.arguments?.getString(AppNavigationGraph.Web.ARGUMENT_URL)
        WebScreen(url = requireNotNull(url))
    }
}