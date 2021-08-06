package app.hdj.datepick.android.ui.components.screens.others.place

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.navArgument
import app.hdj.datepick.android.ui.components.screens.AppNavigationGraph
import com.google.accompanist.navigation.animation.composable

fun NavGraphBuilder.placeScreen() {
    composable(
        AppNavigationGraph.Place.route, listOf(
            navArgument(AppNavigationGraph.Place.ARGUMENT_PLACE_ID) {
                type = NavType.StringType
            }
        )) {
        val placeId =
            it.arguments?.getString(AppNavigationGraph.Place.ARGUMENT_PLACE_ID)
        PlaceScreen(placeId)
    }
}
