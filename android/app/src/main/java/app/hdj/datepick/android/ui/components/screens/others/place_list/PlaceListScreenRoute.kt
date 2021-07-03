package app.hdj.datepick.android.ui.components.screens.others.place_list

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import app.hdj.datepick.android.ui.components.screens.AppNavigationGraph
import app.hdj.datepick.android.ui.components.screens.showPlace
import app.hdj.shared.client.domain.PlaceQuery

fun NavGraphBuilder.placeListScreen(navController: NavController) {

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
        val search =
            it.arguments?.getString(AppNavigationGraph.PlaceList.ARGUMENT_SEARCH)
        val sort =
            it.arguments?.getString(AppNavigationGraph.PlaceList.ARGUMENT_SORT)
                ?: PlaceQuery.Sort.RELATIVE.value

        val placeQuery = PlaceQuery(search, enumValueOf(sort))

        PlaceListScreen(placeQuery, onPlaceClicked = navController.showPlace)
    }

}
