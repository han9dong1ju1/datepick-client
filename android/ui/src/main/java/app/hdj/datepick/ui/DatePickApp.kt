package app.hdj.datepick.ui

import androidx.compose.animation.*
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarResult
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Map
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.*
import app.hdj.datepick.ui.base.DatePickScaffold
import app.hdj.datepick.ui.base.NavigationGraphBottomNavigation
import app.hdj.datepick.ui.components.dialog.appupdate.AppUpdateDialog
import app.hdj.datepick.ui.components.dialog.appupdate.AppUpdateViewModel
import app.hdj.datepick.ui.components.dialog.appupdate.AppUpdateViewModelDelegate
import app.hdj.datepick.ui.navigation.NavigationGraph
import app.hdj.datepick.ui.providers.ProvideParentNavController
import app.hdj.datepick.ui.components.screens.others.splash.SplashScreen
import app.hdj.datepick.ui.components.screens.main.mainScreens
import app.hdj.datepick.ui.components.screens.others.course.CourseScreen
import app.hdj.datepick.ui.components.screens.others.place.PlaceScreen
import app.hdj.datepick.ui.components.screens.others.settings.SettingsScreen
import app.hdj.datepick.ui.utils.extract
import kotlinx.coroutines.launch

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DatePickApp() {

    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val scaffoldState = rememberScaffoldState()

    ProvideParentNavController(navController = navController) {

        DatePickScaffold(
            scaffoldState = scaffoldState,
            bottomBar = {

                val mainNavigationRoutesWithIcon = mapOf(
                    Icons.Rounded.Home to NavigationGraph.Main.Home.route,
                    Icons.Rounded.Map to NavigationGraph.Main.Map.route,
                    Icons.Rounded.Favorite to NavigationGraph.Main.Pick.route,
                    Icons.Rounded.Person to NavigationGraph.Main.Profile.route,
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
                startDestination = NavigationGraph.Main.route
            ) {

                mainScreens()

                composable(NavigationGraph.Place.route, listOf(
                    navArgument(NavigationGraph.Place.ARGUMENT_ID) {
                        type = NavType.LongType
                    }
                )) {
                    PlaceScreen()
                }

                composable(NavigationGraph.Course.route, listOf(
                    navArgument(NavigationGraph.Course.ARGUMENT_ID) {
                        type = NavType.LongType
                    }
                )) {
                    CourseScreen()
                }

                composable(NavigationGraph.Settings.route) {
                    SettingsScreen()
                }

            }

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