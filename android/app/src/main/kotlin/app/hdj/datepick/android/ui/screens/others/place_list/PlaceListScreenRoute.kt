package app.hdj.datepick.android.ui.screens.others.place_list

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import com.google.accompanist.navigation.animation.composable
import androidx.navigation.compose.navArgument
import app.hdj.datepick.android.ui.screens.AppNavigationGraph
import app.hdj.datepick.android.ui.providers.LocalAppNavController

fun NavGraphBuilder.placeListScreen() {

    composable(
        AppNavigationGraph.PlaceList.route, listOf(
            navArgument(AppNavigationGraph.PlaceList.ARGUMENT_SEARCH) {
                type = NavType.StringType
            },
            navArgument(AppNavigationGraph.PlaceList.ARGUMENT_SORT) {
                type = NavType.StringType
            },
        )
    ) {
        val navController = LocalAppNavController.current

        PlaceListScreen()
    }

}
