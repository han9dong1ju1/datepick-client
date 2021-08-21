package app.hdj.datepick.android.ui.screens.others.web

import androidx.navigation.NavGraphBuilder
import app.hdj.datepick.android.ui.screens.AppNavigationGraph
import com.google.accompanist.navigation.animation.composable

fun NavGraphBuilder.webScreen() {
    composable(
        AppNavigationGraph.Web.route,
        AppNavigationGraph.Web.argument(),
    ) {
        val url = it.arguments?.getString(AppNavigationGraph.Web.ARGUMENT_URL)
        WebScreen(url = requireNotNull(url))
    }
}