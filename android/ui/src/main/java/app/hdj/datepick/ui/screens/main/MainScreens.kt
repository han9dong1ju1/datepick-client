package app.hdj.datepick.ui.screens.main

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import app.hdj.datepick.ui.navigation.NavigationGraph
import app.hdj.datepick.ui.screens.main.home.HomeScreen
import app.hdj.datepick.ui.screens.main.map.MapScreen
import app.hdj.datepick.ui.screens.main.pick.PickScreen
import app.hdj.datepick.ui.screens.main.profile.ProfileScreen

fun NavGraphBuilder.mainScreens() {

    navigation(
        startDestination = NavigationGraph.Main.Home.route,
        route = NavigationGraph.Main.route
    ) {

        composable(NavigationGraph.Main.Home.route) { HomeScreen() }

        composable(NavigationGraph.Main.Map.route) { MapScreen() }

        composable(NavigationGraph.Main.Pick.route) { PickScreen() }

        composable(NavigationGraph.Main.Profile.route) { ProfileScreen() }

    }

}