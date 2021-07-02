package app.hdj.datepick.android.ui.components.screens.main

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import app.hdj.datepick.android.ui.components.screens.AppNavigationGraph
import app.hdj.datepick.android.ui.components.screens.main.home.HomeScreen
import app.hdj.datepick.android.ui.components.screens.main.map.MapScreen
import app.hdj.datepick.android.ui.components.screens.main.pick.PickScreen
import app.hdj.datepick.android.ui.components.screens.main.profile.ProfileScreen

fun NavGraphBuilder.mainScreens() {

    navigation(
        startDestination = AppNavigationGraph.Main.Home.route,
        route = AppNavigationGraph.Main.route
    ) {

        composable(AppNavigationGraph.Main.Home.route) { HomeScreen() }

        composable(AppNavigationGraph.Main.Map.route) { MapScreen() }

        composable(AppNavigationGraph.Main.Pick.route) { PickScreen() }

        composable(AppNavigationGraph.Main.Profile.route) { ProfileScreen() }

    }

}