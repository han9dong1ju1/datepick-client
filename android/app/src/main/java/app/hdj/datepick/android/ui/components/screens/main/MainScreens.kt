package app.hdj.datepick.android.ui.components.screens.main

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import app.hdj.datepick.android.ui.components.screens.*
import app.hdj.datepick.android.ui.components.screens.main.home.HomeScreen
import app.hdj.datepick.android.ui.components.screens.main.map.MapScreen
import app.hdj.datepick.android.ui.components.screens.main.pick.PickScreen
import app.hdj.datepick.android.ui.components.screens.main.profile.ProfileScreen
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import app.hdj.shared.client.domain.entity.Course
import app.hdj.shared.client.domain.entity.Place

fun NavGraphBuilder.mainScreens() {


    navigation(
        startDestination = AppNavigationGraph.Main.Home.route,
        route = AppNavigationGraph.Main.route
    ) {

        composable(AppNavigationGraph.Main.Home.route) {
            val navController = LocalAppNavController.current
            HomeScreen(
                onShowMoreCourses = navController.showCourseList,
                onShowMorePlaces = navController.showPlaceList,
                onPlaceClicked = navController.showPlace,
                onCourseClicked = navController.showCourse,
                onSearchPlaceClicked = navController.searchPlace
            )
        }

        composable(AppNavigationGraph.Main.Map.route) {
            MapScreen()
        }

        composable(AppNavigationGraph.Main.Pick.route) {
            PickScreen()
        }

        composable(AppNavigationGraph.Main.Profile.route) {
            ProfileScreen()
        }

    }

}