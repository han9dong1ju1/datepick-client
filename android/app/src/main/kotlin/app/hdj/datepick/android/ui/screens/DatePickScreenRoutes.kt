package app.hdj.datepick.android.ui.screens

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.android.ui.dialog.LocationPermissionDeniedDialog
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import app.hdj.datepick.android.ui.screens.AppNavigationGraph.*
import app.hdj.datepick.android.ui.screens.AppNavigationGraph.Main.*
import app.hdj.datepick.android.ui.screens.AppNavigationGraph.Main.Map
import app.hdj.datepick.android.ui.screens.main.home.HomeScreen
import app.hdj.datepick.android.ui.screens.main.map.MapScreen
import app.hdj.datepick.android.ui.screens.main.myDate.MyDateScreen
import app.hdj.datepick.android.ui.screens.main.profile.ProfileScreen
import app.hdj.datepick.presentation.main.home.HomeScreenViewModel
import app.hdj.datepick.ui.animation.materialTransitionZaxisIn
import app.hdj.datepick.ui.animation.materialTransitionZaxisOut
import com.google.accompanist.navigation.animation.AnimatedNavHost

@Composable
fun DatepickScreenNavHost() {
    val navController = LocalAppNavController.current

    val homeScreenViewModel = hiltViewModel<HomeScreenViewModel>()

    AnimatedNavHost(
        navController = navController,
        startDestination = Splash.route,
        enterTransition = { materialTransitionZaxisIn },
        exitTransition = { materialTransitionZaxisOut }
    ) {
        navGraphBuildScope {

            Splash {

            }

            nestedNavigation(Home, Main) {
                Home { HomeScreen(homeScreenViewModel) }
                Map { MapScreen() }
                MyDate { MyDateScreen() }
                Profile { ProfileScreen() }
            }

            LoginDialog {

            }

            ExitDialog {

            }

            LocationPermissionDeniedDialog { LocationPermissionDeniedDialog() }

        }

    }
}