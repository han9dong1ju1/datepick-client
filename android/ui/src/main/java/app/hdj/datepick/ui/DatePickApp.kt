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
import app.hdj.datepick.ui.screens.course.CourseScreen
import app.hdj.datepick.ui.screens.login.LoginScreen
import app.hdj.datepick.ui.screens.main.home.HomeScreen
import app.hdj.datepick.ui.screens.main.map.MapScreen
import app.hdj.datepick.ui.screens.main.pick.PickScreen
import app.hdj.datepick.ui.screens.main.profile.ProfileScreen
import app.hdj.datepick.ui.screens.place.PlaceScreen
import app.hdj.datepick.ui.screens.setting.SettingScreen
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
                        NavigationGraph.Home.route,
                        NavigationGraph.Profile.route,
                        NavigationGraph.Map.route,
                        NavigationGraph.Pick.route,
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
                    startDestination = NavigationGraph.Login.route
                ) {

                    composable(NavigationGraph.Login.route) { LoginScreen() }

                    /* Main */
                    composable(NavigationGraph.Home.route) { HomeScreen() }

                    composable(NavigationGraph.Map.route) { MapScreen() }

                    composable(NavigationGraph.Pick.route) { PickScreen() }

                    composable(NavigationGraph.Profile.route) { ProfileScreen() }
                    /* Main */

                    composable(NavigationGraph.Place.route, listOf(
                        navArgument(NavigationGraph.Place.ARGUMENT_ID) {
                            type = NavType.LongType
                        }
                    )) {
                        val placeId = it.arguments?.getLong(NavigationGraph.Place.ARGUMENT_ID)
                            ?: return@composable

                        PlaceScreen()
                    }

                    composable(NavigationGraph.Course.route, listOf(
                        navArgument(NavigationGraph.Course.ARGUMENT_ID) {
                            type = NavType.LongType
                        }
                    )) {
                        val courseId = it.arguments?.getLong(NavigationGraph.Course.ARGUMENT_ID)
                            ?: return@composable

                        CourseScreen()
                    }

                    composable(NavigationGraph.Settings.route) { SettingScreen() }
                }

            }

        }

    }

}