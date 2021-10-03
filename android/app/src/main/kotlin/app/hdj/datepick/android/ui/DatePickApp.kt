package app.hdj.datepick.android.ui

import androidx.compose.animation.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Map
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.plusAssign
import app.hdj.datepick.android.ui.DatePickAppViewModelDelegate.Event.ChangeStatusBarMode
import app.hdj.datepick.android.ui.dialog.appupdate.appUpdateDialog
import app.hdj.datepick.android.ui.dialog.login.loginDialog
import app.hdj.datepick.android.ui.screens.AppNavigationGraph
import app.hdj.datepick.android.ui.screens.main.MainBottomNavigation
import app.hdj.datepick.android.ui.screens.main.mainScreens
import app.hdj.datepick.android.ui.screens.others.course.courseScreen
import app.hdj.datepick.android.ui.screens.others.createCourse.createCourseScreen
import app.hdj.datepick.android.ui.screens.others.featuredDetail.featuredDetailScreen
import app.hdj.datepick.android.ui.screens.others.placeDetail.placeDetailScreen
import app.hdj.datepick.android.ui.screens.others.placeList.placeListScreen
import app.hdj.datepick.android.ui.screens.others.settings.settingsScreens
import app.hdj.datepick.android.ui.providers.*
import app.hdj.datepick.android.ui.screens.main.home.fakeHomeViewModel
import app.hdj.datepick.android.ui.screens.main.map.fakeMapViewModel
import app.hdj.datepick.android.ui.screens.main.pick.fakePickViewModel
import app.hdj.datepick.android.ui.screens.main.menu.fakeMenuViewModel
import app.hdj.datepick.android.ui.screens.others.image.imagesScreen
import app.hdj.datepick.android.ui.screens.others.userProfileEdit.userProfileEditScreenRoute
import app.hdj.datepick.android.ui.screens.others.web.webScreen
import app.hdj.datepick.ui.animation.materialTransitionZaxisIn
import app.hdj.datepick.ui.animation.materialTransitionZaxisOut
import app.hdj.datepick.ui.components.BottomNavigationProperty
import app.hdj.datepick.ui.components.BaseScaffold
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


@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialNavigationApi::class)
@Composable
fun DatePickApp() {

    val navController = rememberAnimatedNavController()

    val bottomSheetNavigator = rememberBottomSheetNavigator()

    navController.navigatorProvider += bottomSheetNavigator

    val scaffoldState = rememberScaffoldState()

    val coroutineScope = rememberCoroutineScope()

    val snackBarPresenter = remember { SnackbarPresenter(coroutineScope, scaffoldState) }

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val currentRoute = navBackStackEntry?.destination?.route

    val appViewModel = LocalDatePickAppViewModel.current

//    val homeViewModel = hiltViewModel<HomeViewModel>()
//    val mapViewModel = hiltViewModel<MapViewModel>()
//    val pickViewModel = hiltViewModel<PickViewModel>()
//    val profileViewModel = hiltViewModel<ProfileViewModel>()

    val homeViewModel = fakeHomeViewModel()
    val mapViewModel = fakeMapViewModel()
    val pickViewModel = fakePickViewModel()
    val profileViewModel = fakeMenuViewModel()

    CompositionLocalProvider(
        LocalAppNavController provides navController,
        LocalSnackBarPresenter provides snackBarPresenter
    ) {

        BaseScaffold(
            scaffoldState = scaffoldState,
            bottomBar = {
                MainBottomNavigation(mainNavigationRoutesWithIcon, currentRoute)
            }
        ) {

            ModalBottomSheetLayout(bottomSheetNavigator = bottomSheetNavigator) {

                AnimatedNavHost(
                    modifier = Modifier.fillMaxSize(),
                    navController = navController,
                    startDestination = AppNavigationGraph.Main.route,
                    enterTransition = { _, _ -> materialTransitionZaxisIn },
                    exitTransition = { _, _ -> materialTransitionZaxisOut }
                ) {

                    /* Main Screens */
                    mainScreens(homeViewModel, mapViewModel, pickViewModel, profileViewModel)

                    /* Other Screens */
                    featuredDetailScreen()

                    placeDetailScreen()
                    placeListScreen()

                    courseScreen()
                    createCourseScreen()

                    /* Setting Screens */
                    settingsScreens()

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
