package app.hdj.datepick.android.ui.components.screens.others.search_place

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import app.hdj.datepick.android.ui.components.screens.AppNavigationGraph

fun NavGraphBuilder.searchPlaceScreen() {
    composable(AppNavigationGraph.SearchPlace.route) {
        SearchPlaceScreen()
    }
}
