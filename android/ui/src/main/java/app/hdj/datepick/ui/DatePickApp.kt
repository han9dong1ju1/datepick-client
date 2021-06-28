package app.hdj.datepick.ui

import androidx.compose.animation.*
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Map
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.navigation.NavType
import androidx.navigation.compose.*
import app.hdj.datepick.ui.components.DatePickScaffold
import app.hdj.datepick.ui.components.NavigationGraphBottomNavigation
import app.hdj.datepick.ui.navigation.NavigationGraph
import app.hdj.datepick.ui.providers.LocalMeState
import app.hdj.datepick.ui.providers.ProvideParentNavController
import app.hdj.datepick.ui.screens.SplashScreen
import app.hdj.datepick.ui.screens.main.mainScreens
import app.hdj.datepick.ui.screens.others.course.CourseScreen
import app.hdj.datepick.ui.screens.others.place.PlaceScreen
import app.hdj.datepick.ui.screens.others.settings.SettingsScreen
import app.hdj.datepick.ui.utils.currentScreenRoute
import app.hdj.shared.client.domain.StateData
import com.google.accompanist.insets.ui.BottomNavigation

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DatePickApp() {

    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    ProvideParentNavController(navController = navController) {

        DatePickScaffold(
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

}