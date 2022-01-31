package app.hdj.datepick.android.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.android.ui.dialog.LocationPermissionDeniedDialog
import app.hdj.datepick.android.ui.icons.DatePickIcon
import app.hdj.datepick.android.ui.icons.DatePickIcons
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import app.hdj.datepick.android.ui.screens.AppNavigationGraph.*
import app.hdj.datepick.android.ui.screens.AppNavigationGraph.CreateCourse.CourseTheme
import app.hdj.datepick.android.ui.screens.AppNavigationGraph.CreateCourse.Info
import app.hdj.datepick.android.ui.screens.AppNavigationGraph.Main.*
import app.hdj.datepick.android.ui.screens.AppNavigationGraph.Main.Map
import app.hdj.datepick.android.ui.screens.createCourse.CreateCourseInfoScreen
import app.hdj.datepick.android.ui.screens.createCourse.CreateCourseThemeScreen
import app.hdj.datepick.android.ui.screens.featured.FeaturedDetailScreen
import app.hdj.datepick.android.ui.screens.main.home.HomeScreen
import app.hdj.datepick.android.ui.screens.main.map.MapScreen
import app.hdj.datepick.android.ui.screens.main.myDate.MyDateScreen
import app.hdj.datepick.android.ui.screens.main.profile.ProfileScreen
import app.hdj.datepick.android.ui.screens.notifications.NotificationsScreen
import app.hdj.datepick.android.ui.screens.place.PlaceScreen
import app.hdj.datepick.presentation.createCourse.CreateCourseScreenViewModel
import app.hdj.datepick.presentation.main.HomeScreenViewModel
import app.hdj.datepick.ui.animation.materialTransitionZaxisIn
import app.hdj.datepick.ui.animation.materialTransitionZaxisOut
import com.google.accompanist.navigation.animation.AnimatedNavHost
import kotlinx.coroutines.delay

@Composable
fun DatepickScreenNavHost() {

    val context = LocalContext.current

    val navController = LocalAppNavController.current

    val homeScreenViewModel = hiltViewModel<HomeScreenViewModel>()

    val createCourseScreenViewModel = hiltViewModel<CreateCourseScreenViewModel>()

    AnimatedNavHost(
        navController = navController,
        startDestination = Splash.route,
        enterTransition = { materialTransitionZaxisIn },
        exitTransition = { materialTransitionZaxisOut }
    ) {
        navGraphBuildScope {

            Splash {
                LaunchedEffect(true) {
                    delay(1500)
                    navController.navigateRoute(Main)
                }
                Box(
                    modifier = Modifier.fillMaxSize().background(MaterialTheme.colors.background)
                ) {
                    Icon(
                        imageVector = DatePickIcons.DatePickIcon,
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier.size(100.dp).align(Alignment.Center)
                    )
                }
            }

            nestedNavigation(CourseTheme, CreateCourse) {
                CourseTheme {
                    CreateCourseThemeScreen(createCourseScreenViewModel)
                }
                Info {
                    CreateCourseInfoScreen(createCourseScreenViewModel)
                }
            }

            nestedNavigation(Home, Main) {
                Home { HomeScreen(homeScreenViewModel) }
                Map { MapScreen() }
                MyDate { MyDateScreen() }
                Profile { ProfileScreen() }
            }

            FeaturedDetail {
                val featuredId = it.arguments?.getLong(FeaturedDetail.ARGUMENT_FEATURED_ID)
                FeaturedDetailScreen(featuredId!!)
            }

            PlaceDetail {
                val placeId = it.arguments?.getLong(PlaceDetail.ARGUMENT_PLACE_ID)
                PlaceScreen(placeId)
            }

            Notifications {
                NotificationsScreen()
            }

            LoginDialog {

            }

            ExitDialog {

            }

            LocationPermissionDeniedDialog { LocationPermissionDeniedDialog() }

        }

    }
}