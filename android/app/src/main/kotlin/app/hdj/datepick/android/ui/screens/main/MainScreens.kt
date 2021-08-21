package app.hdj.datepick.android.ui.screens.main

import androidx.navigation.NavGraphBuilder
import app.hdj.datepick.android.ui.screens.AppNavigationGraph
import app.hdj.datepick.android.ui.screens.main.home.HomeScreen
import app.hdj.datepick.android.ui.screens.main.home.HomeViewModelDelegate
import app.hdj.datepick.android.ui.screens.main.map.MapScreen
import app.hdj.datepick.android.ui.screens.main.map.MapViewModelDelegate
import app.hdj.datepick.android.ui.screens.main.pick.PickScreen
import app.hdj.datepick.android.ui.screens.main.pick.PickViewModelDelegate
import app.hdj.datepick.android.ui.screens.main.profile.ProfileScreen
import app.hdj.datepick.android.ui.screens.main.profile.ProfileViewModelDelegate
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import app.hdj.datepick.android.ui.screens.AppNavigationGraph.Main.*
import app.hdj.datepick.android.ui.screens.AppNavigationGraph.Main.Map
import app.hdj.datepick.android.ui.screens.appNavigationComposable
import com.google.accompanist.navigation.animation.navigation

fun NavGraphBuilder.mainScreens(
    homeViewModel: HomeViewModelDelegate,
    mapViewModel: MapViewModelDelegate,
    pickViewModel: PickViewModelDelegate,
    profileViewModel: ProfileViewModelDelegate
) {

    navigation(
        startDestination = Home.route,
        route = AppNavigationGraph.Main.route
    ) {

        appNavigationComposable(Home) {
            val navController = LocalAppNavController.current
            HomeScreen(
                homeViewModel
            )
        }

        appNavigationComposable(Map) {
            MapScreen(
                mapViewModel
            )
        }

        appNavigationComposable(Pick) {
            PickScreen(
                pickViewModel
            )
        }

        appNavigationComposable(Profile) {
            val navController = LocalAppNavController.current
            ProfileScreen(
                profileViewModel,
                onSettingClicked = {}
            )
        }

    }

}