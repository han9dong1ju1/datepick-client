package app.hdj.datepick.android.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Map
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import app.hdj.datepick.android.ui.components.dialog.appupdate.appUpdateDialog
import app.hdj.datepick.android.ui.components.dialog.login.loginDialog
import app.hdj.datepick.android.ui.components.screens.AppNavigationGraph
import app.hdj.datepick.android.ui.components.screens.main.MainBottomNavigation
import app.hdj.datepick.android.ui.components.screens.main.home.HomeViewModel
import app.hdj.datepick.android.ui.components.screens.main.mainScreens
import app.hdj.datepick.android.ui.components.screens.main.map.MapViewModel
import app.hdj.datepick.android.ui.components.screens.main.pick.PickViewModel
import app.hdj.datepick.android.ui.components.screens.main.profile.ProfileViewModel
import app.hdj.datepick.android.ui.components.screens.others.course.courseScreen
import app.hdj.datepick.android.ui.components.screens.others.create_course.createCourseScreen
import app.hdj.datepick.android.ui.components.screens.others.place.placeScreen
import app.hdj.datepick.android.ui.components.screens.others.place_list.placeListScreen
import app.hdj.datepick.android.ui.components.screens.others.settings.settingsScreens
import app.hdj.datepick.android.ui.components.screens.others.splash.SplashScreen
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import app.hdj.datepick.android.ui.providers.LocalSnackBarPresenter
import app.hdj.datepick.android.ui.providers.SnackbarPresenter
import app.hdj.datepick.ui.components.BottomNavigationProperty
import app.hdj.datepick.ui.components.DatePickScaffold

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DatePickApp() {

    val navController = rememberNavController()

    val scaffoldState = rememberScaffoldState()

    val coroutineScope = rememberCoroutineScope()

    val snackBarPresenter = remember { SnackbarPresenter(coroutineScope, scaffoldState) }

    var pickBadgeStatus by remember { mutableStateOf(true) }

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val currentRoute = navBackStackEntry?.destination?.route

    if (AppNavigationGraph.Main.Pick.route == currentRoute) pickBadgeStatus = false

    val homeViewModel: HomeViewModel = hiltViewModel()
    val mapViewModel: MapViewModel = hiltViewModel()
    val pickViewModel: PickViewModel = hiltViewModel()
    val profileViewModel: ProfileViewModel = hiltViewModel()

    CompositionLocalProvider(
        LocalAppNavController provides navController,
        LocalSnackBarPresenter provides snackBarPresenter
    ) {

        DatePickScaffold(
            scaffoldState = scaffoldState,
            bottomBar = {

                val mainNavigationRoutesWithIcon = listOf(
                    BottomNavigationProperty(Icons.Rounded.Home, "홈", AppNavigationGraph.Main.Home),
                    BottomNavigationProperty(Icons.Rounded.Map, "지도", AppNavigationGraph.Main.Map),
                    BottomNavigationProperty(
                        Icons.Rounded.Favorite,
                        "픽",
                        AppNavigationGraph.Main.Pick
                    ),
                    BottomNavigationProperty(
                        Icons.Rounded.Person,
                        "프로필",
                        AppNavigationGraph.Main.Profile
                    ),
                )

                MainBottomNavigation(mainNavigationRoutesWithIcon, currentRoute)
            }
        ) {

            NavHost(
                navController = navController,
                startDestination = AppNavigationGraph.Main.route
            ) {

                /* Main Screens */
                mainScreens(homeViewModel, mapViewModel, pickViewModel, profileViewModel)

                /* Other Screens */
                placeScreen()
                courseScreen()
                placeListScreen()

                createCourseScreen()

                /* Setting Screens */
                settingsScreens()

                appUpdateDialog()
                loginDialog()
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
}
