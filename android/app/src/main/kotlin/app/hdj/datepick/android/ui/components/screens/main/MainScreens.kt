package app.hdj.datepick.android.ui.components.screens.main

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import app.hdj.datepick.android.ui.components.screens.AppNavigationGraph
import app.hdj.datepick.android.ui.components.screens.main.home.HomeScreen
import app.hdj.datepick.android.ui.components.screens.main.home.HomeViewModel
import app.hdj.datepick.android.ui.components.screens.main.map.MapScreen
import app.hdj.datepick.android.ui.components.screens.main.map.MapViewModel
import app.hdj.datepick.android.ui.components.screens.main.pick.PickScreen
import app.hdj.datepick.android.ui.components.screens.main.pick.PickViewModel
import app.hdj.datepick.android.ui.components.screens.main.profile.ProfileScreen
import app.hdj.datepick.android.ui.components.screens.main.profile.ProfileViewModel
import app.hdj.datepick.android.ui.components.screens.showSetting
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import com.google.accompanist.navigation.animation.navigation
import timber.log.Timber
import kotlin.math.roundToInt

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