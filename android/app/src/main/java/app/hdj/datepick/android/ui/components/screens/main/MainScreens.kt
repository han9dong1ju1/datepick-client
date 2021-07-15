package app.hdj.datepick.android.ui.components.screens.main

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import app.hdj.datepick.android.ui.components.screens.*
import app.hdj.datepick.android.ui.components.screens.main.home.HomeScreen
import app.hdj.datepick.android.ui.components.screens.main.home.HomeViewModel
import app.hdj.datepick.android.ui.components.screens.main.map.MapScreen
import app.hdj.datepick.android.ui.components.screens.main.map.MapViewModel
import app.hdj.datepick.android.ui.components.screens.main.pick.PickScreen
import app.hdj.datepick.android.ui.components.screens.main.pick.PickViewModel
import app.hdj.datepick.android.ui.components.screens.main.profile.ProfileScreen
import app.hdj.datepick.android.ui.components.screens.main.profile.ProfileViewModel
import app.hdj.datepick.android.ui.providers.LocalAppNavController

fun NavGraphBuilder.mainScreens(
    homeViewModel: HomeViewModel,
    mapViewModel: MapViewModel,
    pickViewModel: PickViewModel,
    profileViewModel: ProfileViewModel
) {

    navigation(
        startDestination = AppNavigationGraph.Main.Home.route,
        route = AppNavigationGraph.Main.route
    ) {

        composable(AppNavigationGraph.Main.Home.route) {
            val navController = LocalAppNavController.current
            HomeScreen(
                homeViewModel,
                onShowMoreCoursesClicked = navController.showCourseList,
                onShowMorePlacesClicked = navController.showPlaceList,
                onPlaceClicked = navController.showPlace,
                onCourseClicked = navController.showCourse,
            )
        }

        composable(AppNavigationGraph.Main.Map.route) {
            MapScreen(
                mapViewModel
            )
        }

        composable(AppNavigationGraph.Main.Pick.route) {
            PickScreen(
                pickViewModel
            )
        }

        composable(AppNavigationGraph.Main.Profile.route) {
            val navController = LocalAppNavController.current
            ProfileScreen(
                profileViewModel,
                onSettingClicked = navController.showSetting
            )
        }

    }

}