package app.hdj.datepick.android.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Map
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.toSize
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.plusAssign
import app.hdj.datepick.android.ui.components.dialog.appupdate.appUpdateDialog
import app.hdj.datepick.android.ui.components.dialog.login.loginDialog
import app.hdj.datepick.android.ui.components.screens.AppNavigationGraph
import app.hdj.datepick.android.ui.components.screens.main.MainBottomNavigation
import app.hdj.datepick.android.ui.components.screens.main.home.HomeViewModel
import app.hdj.datepick.android.ui.components.screens.main.home.fakeHomeViewModel
import app.hdj.datepick.android.ui.components.screens.main.mainScreens
import app.hdj.datepick.android.ui.components.screens.main.map.MapViewModel
import app.hdj.datepick.android.ui.components.screens.main.pick.PickViewModel
import app.hdj.datepick.android.ui.components.screens.main.profile.ProfileViewModel
import app.hdj.datepick.android.ui.components.screens.others.course.courseScreen
import app.hdj.datepick.android.ui.components.screens.others.create_course.createCourseScreen
import app.hdj.datepick.android.ui.components.screens.others.place.placeScreen
import app.hdj.datepick.android.ui.components.screens.others.place_list.placeListScreen
import app.hdj.datepick.android.ui.components.screens.others.settings.settingsScreens
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import app.hdj.datepick.android.ui.providers.LocalSnackBarPresenter
import app.hdj.datepick.android.ui.providers.LocalSystemUiController
import app.hdj.datepick.android.ui.providers.SnackbarPresenter
import app.hdj.datepick.ui.components.BottomNavigationProperty
import app.hdj.datepick.ui.components.DatePickScaffold
import com.google.accompanist.navigation.animation.AnimatedComposeNavigator
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlin.math.roundToInt

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialNavigationApi::class)
@Composable
fun DatePickApp() {

    val navController = rememberNavController()

    val animatedComposeNavigator = remember { AnimatedComposeNavigator() }
    val bottomSheetNavigator = rememberBottomSheetNavigator()

    navController.navigatorProvider += bottomSheetNavigator
    navController.navigatorProvider += animatedComposeNavigator

    val scaffoldState = rememberScaffoldState()

    val coroutineScope = rememberCoroutineScope()

    val snackBarPresenter = remember { SnackbarPresenter(coroutineScope, scaffoldState) }

    var pickBadgeStatus by remember { mutableStateOf(true) }

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val currentRoute = navBackStackEntry?.destination?.route

    val systemUiController = rememberSystemUiController()

    if (AppNavigationGraph.Main.Home.route == currentRoute) {
        systemUiController.setSystemBarsColor(Color.Transparent, darkIcons = false)
    } else {
        systemUiController.setSystemBarsColor(
            Color.White.copy(alpha = 0.5f),
            darkIcons = MaterialTheme.colors.isLight
        )
        if (AppNavigationGraph.Main.Pick.route == currentRoute) pickBadgeStatus = false
    }

    val homeViewModel = fakeHomeViewModel()
    val mapViewModel = hiltViewModel<MapViewModel>()
    val pickViewModel = hiltViewModel<PickViewModel>()
    val profileViewModel = hiltViewModel<ProfileViewModel>()

    CompositionLocalProvider(
        LocalSystemUiController provides systemUiController,
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

            ModalBottomSheetLayout(bottomSheetNavigator = bottomSheetNavigator) {

                AnimatedNavHost(
                    modifier = Modifier.fillMaxSize(),
                    navController = navController,
                    startDestination = AppNavigationGraph.Main.route,
                    enterTransition = { _, _ ->
                        fadeIn(
                            animationSpec = tween(
                                210,
                                90,
                                easing = LinearOutSlowInEasing
                            )
                        ) + expandIn(
                            animationSpec = tween(210, 90, easing = LinearOutSlowInEasing),
                            expandFrom = Alignment.Center,
                            initialSize = {
                                val size = it.toSize() * 0.98f
                                IntSize(size.width.roundToInt(), size.height.roundToInt())
                            },
                            clip = false
                        )
                    },
                    exitTransition = { _, _ ->
                        fadeOut(
                            animationSpec = tween(
                                90,
                                easing = LinearOutSlowInEasing
                            )
                        )
                    }
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

        }

    }
}
