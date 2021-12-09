package app.hdj.datepick.android.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Map
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.plusAssign
import app.hdj.datepick.android.ui.dialog.appupdate.appUpdateDialog
import app.hdj.datepick.android.ui.dialog.login.loginDialog
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import app.hdj.datepick.android.ui.screens.AppNavigationGraph
import app.hdj.datepick.android.ui.screens.main.MainBottomNavigation
import app.hdj.datepick.android.ui.screens.main.MainFloatingActionButton
import app.hdj.datepick.android.ui.screens.main.home.HomeViewModel
import app.hdj.datepick.android.ui.screens.main.mainScreens
import app.hdj.datepick.android.ui.screens.main.map.MapViewModel
import app.hdj.datepick.android.ui.screens.main.menu.MenuViewModel
import app.hdj.datepick.android.ui.screens.main.pick.PickViewModel
import app.hdj.datepick.android.ui.screens.others.appSettings.appSettingsScreen
import app.hdj.datepick.android.ui.screens.others.course.courseScreen
import app.hdj.datepick.android.ui.screens.others.createCourse.createCourseScreen
import app.hdj.datepick.android.ui.screens.others.featuredDetail.featuredDetailScreen
import app.hdj.datepick.android.ui.screens.others.image.imagesScreen
import app.hdj.datepick.android.ui.screens.others.placeDetail.placeDetailScreen
import app.hdj.datepick.android.ui.screens.others.placeList.placeListScreen
import app.hdj.datepick.android.ui.screens.others.userProfileEdit.userProfileEditScreenRoute
import app.hdj.datepick.android.ui.screens.others.web.webScreen
import app.hdj.datepick.ui.animation.materialTransitionZaxisIn
import app.hdj.datepick.ui.animation.materialTransitionZaxisOut
import app.hdj.datepick.ui.components.BaseScaffold
import app.hdj.datepick.ui.components.BottomNavigationProperty
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator


private val mainNavigationRoutesWithIcon = listOf(
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


@OptIn(
    ExperimentalAnimationApi::class,
    ExperimentalMaterialNavigationApi::class,
    ExperimentalMaterial3Api::class
)
@Composable
fun DatePickApp() {

    val navController = rememberAnimatedNavController()

    val bottomSheetNavigator = rememberBottomSheetNavigator()

    navController.navigatorProvider += bottomSheetNavigator

    val coroutineScope = rememberCoroutineScope()

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val currentRoute = remember(navBackStackEntry?.destination?.route) {
        navBackStackEntry?.destination?.route
    }

    val homeViewModel = hiltViewModel<HomeViewModel>()
    val mapViewModel = hiltViewModel<MapViewModel>()
    val pickViewModel = hiltViewModel<PickViewModel>()
    val menuViewModel = hiltViewModel<MenuViewModel>()

    CompositionLocalProvider(
        LocalAppNavController provides navController
    ) {

        BaseScaffold(
            bottomBar = { MainBottomNavigation(mainNavigationRoutesWithIcon, currentRoute) },
            floatingActionButton = { MainFloatingActionButton(currentRoute) },
        ) {

            ModalBottomSheetLayout(
                bottomSheetNavigator = bottomSheetNavigator,
                sheetBackgroundColor = Color.Unspecified
            ) {

                AnimatedNavHost(
                    modifier = Modifier.fillMaxSize(),
                    navController = navController,
                    startDestination = AppNavigationGraph.Main.route,
                    enterTransition = { materialTransitionZaxisIn },
                    exitTransition = { materialTransitionZaxisOut }
                ) {

                    /* Main Screens */
                    mainScreens(homeViewModel, mapViewModel, pickViewModel, menuViewModel)

                    /* Other Screens */
                    featuredDetailScreen()

                    placeDetailScreen()
                    placeListScreen()

                    courseScreen()
                    createCourseScreen()

                    /* Setting Screens */
                    appSettingsScreen()

                    webScreen()
                    imagesScreen()
                    userProfileEditScreenRoute()

                    appUpdateDialog()
                    loginDialog()
                }

            }

        }

    }

}
