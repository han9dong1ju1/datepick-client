package app.hdj.datepick.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import app.hdj.datepick.ui.navigation.NavigationGraph
import app.hdj.datepick.ui.screens.others.course.CourseScreen
import app.hdj.datepick.ui.screens.main.mainScreens
import app.hdj.datepick.ui.screens.onboarding.onBoardingScreens
import app.hdj.datepick.ui.screens.others.place.PlaceScreen
import app.hdj.datepick.ui.screens.others.settings.SettingsScreen
import app.hdj.datepick.ui.styles.DatePickTheme
import app.hdj.datepick.ui.utils.currentScreenRoute
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.ui.BottomNavigation

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DatePickApp() {

    DatePickTheme {

        ProvideWindowInsets {

            val navController = rememberNavController()

            val route = navController.currentScreenRoute()

            Scaffold(
                bottomBar = {

                    val allowedRoutes = listOf(
                        NavigationGraph.Main.Home.route,
                        NavigationGraph.Main.Profile.route,
                        NavigationGraph.Main.Map.route,
                        NavigationGraph.Main.Pick.route,
                    )

                    val isRouteAllowedForBottomNavigation = allowedRoutes.contains(route)

                    AnimatedVisibility(visible = isRouteAllowedForBottomNavigation) {
                        BottomNavigation {

                        }
                    }
                }
            ) {

                NavHost(
                    navController = navController,
                    startDestination = NavigationGraph.OnBoarding.route
                ) {

                    onBoardingScreens()

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

    }

}