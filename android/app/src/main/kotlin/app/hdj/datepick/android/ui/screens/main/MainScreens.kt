package app.hdj.datepick.android.ui.screens.main

import androidx.navigation.NavGraphBuilder
import app.hdj.datepick.android.ui.screens.AppNavigationGraph
import app.hdj.datepick.android.ui.screens.main.home.HomeScreen
import app.hdj.datepick.android.ui.screens.main.home.HomeViewModelDelegate
import app.hdj.datepick.android.ui.screens.main.map.MapScreen
import app.hdj.datepick.android.ui.screens.main.map.MapViewModelDelegate
import app.hdj.datepick.android.ui.screens.main.pick.PickScreen
import app.hdj.datepick.android.ui.screens.main.pick.PickViewModelDelegate
import app.hdj.datepick.android.ui.screens.main.menu.MenuScreen
import app.hdj.datepick.android.ui.screens.main.menu.MenuViewModelDelegate
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import app.hdj.datepick.android.ui.screens.AppNavigationGraph.Main.*
import app.hdj.datepick.android.ui.screens.AppNavigationGraph.Main.Map
import app.hdj.datepick.android.ui.screens.appNavigationComposable
import app.hdj.datepick.ui.animation.materialTransitionYaxisIn
import app.hdj.datepick.ui.animation.materialTransitionYaxisOut
import app.hdj.datepick.ui.animation.materialTransitionZaxisIn
import app.hdj.datepick.ui.animation.materialTransitionZaxisOut
import com.google.accompanist.navigation.animation.navigation

fun NavGraphBuilder.mainScreens(
    homeViewModel: HomeViewModelDelegate,
    mapViewModel: MapViewModelDelegate,
    pickViewModel: PickViewModelDelegate,
    menuViewModel: MenuViewModelDelegate
) {

    navigation(
        startDestination = Home.route,
        route = AppNavigationGraph.Main.route,
        enterTransition = { materialTransitionYaxisIn() },
        exitTransition = { materialTransitionYaxisOut(false)  }
    ) {

        appNavigationComposable(Home) {
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
            MenuScreen(
                menuViewModel
            )
        }

    }

}