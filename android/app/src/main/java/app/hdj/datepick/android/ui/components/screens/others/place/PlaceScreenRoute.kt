package app.hdj.datepick.android.ui.components.screens.others.place

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import app.hdj.datepick.android.ui.components.screens.AppNavigationGraph

fun NavGraphBuilder.placeScreen(navController: NavHostController) {
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
