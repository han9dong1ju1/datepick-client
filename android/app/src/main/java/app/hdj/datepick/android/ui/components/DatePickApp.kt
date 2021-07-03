package app.hdj.datepick.android.ui.components

import androidx.compose.animation.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Map
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.*
import app.hdj.datepick.android.ui.components.dialog.appupdate.AppUpdateDialog
import app.hdj.datepick.android.ui.components.screens.AppNavigationGraph
import app.hdj.datepick.android.ui.components.screens.main.mainScreens
import app.hdj.datepick.android.ui.components.screens.others.course.courseScreen
import app.hdj.datepick.android.ui.components.screens.others.place.placeScreen
import app.hdj.datepick.android.ui.components.screens.others.place_list.placeListScreen
import app.hdj.datepick.android.ui.components.screens.others.settings.settingsScreens
import app.hdj.datepick.android.ui.components.screens.others.splash.SplashScreen
import app.hdj.datepick.android.ui.providers.ProvideLocalSnackBarPresenter
import app.hdj.datepick.ui.components.DatePickScaffold
import app.hdj.datepick.ui.components.NavigationGraphBottomNavigation

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DatePickApp() {

    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val scaffoldState = rememberScaffoldState()

    ProvideLocalSnackBarPresenter(scaffoldState) {

        DatePickScaffold(
            scaffoldState = scaffoldState,
            bottomBar = {

                val mainNavigationRoutesWithIcon = mapOf(
                    Icons.Rounded.Home to AppNavigationGraph.Main.Home.route,
                    Icons.Rounded.Map to AppNavigationGraph.Main.Map.route,
                    Icons.Rounded.Favorite to AppNavigationGraph.Main.Pick.route,
                    Icons.Rounded.Person to AppNavigationGraph.Main.Profile.route,
                )

                val currentRoute = navBackStackEntry?.destination?.route

                val isRouteAllowedForBottomNavigation =
                    mainNavigationRoutesWithIcon.values.contains(currentRoute)

                AnimatedVisibility(
                    visible = isRouteAllowedForBottomNavigation,
                    enter = slideInVertically(initialOffsetY = { it / 2 }) + fadeIn(),
                    exit = slideOutVertically(targetOffsetY = { it / 2 }) + fadeOut()
                ) {
                    NavigationGraphBottomNavigation(
                        navController,
                        mainNavigationRoutesWithIcon
                    )
                }

            }
        ) {

            NavHost(
                navController = navController,
                startDestination = AppNavigationGraph.Main.route
            ) {

                /* Main Screens */
                mainScreens(navController)

                /* Other Screens */
                placeScreen(navController)

                courseScreen(navController)

                placeListScreen(navController)

                /* Setting Screens */
                settingsScreens(navController)

            }

        }


        val (splashVisibleState, onSplashVisibleStateChange) = remember { mutableStateOf(true) }

        AnimatedVisibility(
            visible = splashVisibleState,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            SplashScreen {
                onSplashVisibleStateChange(false)
            }
        }

        AppUpdateDialog()

    }
}
