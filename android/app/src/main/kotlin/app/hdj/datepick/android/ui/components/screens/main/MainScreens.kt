package app.hdj.datepick.android.ui.components.screens.main

import androidx.navigation.NavGraphBuilder
import app.hdj.datepick.android.ui.components.screens.AppNavigationGraph
import app.hdj.datepick.android.ui.components.screens.main.home.HomeScreen
import app.hdj.datepick.android.ui.components.screens.main.home.HomeViewModelDelegate
import app.hdj.datepick.android.ui.components.screens.main.map.MapScreen
import app.hdj.datepick.android.ui.components.screens.main.map.MapViewModelDelegate
import app.hdj.datepick.android.ui.components.screens.main.pick.PickScreen
import app.hdj.datepick.android.ui.components.screens.main.pick.PickViewModelDelegate
import app.hdj.datepick.android.ui.components.screens.main.profile.ProfileScreen
import app.hdj.datepick.android.ui.components.screens.main.profile.ProfileViewModelDelegate
import app.hdj.datepick.android.ui.components.screens.showSetting
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation

fun NavGraphBuilder.mainScreens(
    homeViewModel: HomeViewModelDelegate,
    mapViewModel: MapViewModelDelegate,
    pickViewModel: PickViewModelDelegate,
    profileViewModel: ProfileViewModelDelegate
) {

    navigation(
        startDestination = AppNavigationGraph.Main.Home.route,
        route = AppNavigationGraph.Main.route
    ) {

        composable(AppNavigationGraph.Main.Home.route) {
            val navController = LocalAppNavController.current
            HomeScreen(
                homeViewModel
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