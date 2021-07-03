package app.hdj.datepick.android.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import app.hdj.datepick.android.ui.components.dialog.appupdate.AppUpdateDialog
import app.hdj.datepick.android.ui.components.screens.AppNavigationGraph
import app.hdj.datepick.android.ui.components.screens.main.MainBottomNavigation
import app.hdj.datepick.android.ui.components.screens.main.mainScreens
import app.hdj.datepick.android.ui.components.screens.others.course.courseScreen
import app.hdj.datepick.android.ui.components.screens.others.place.placeScreen
import app.hdj.datepick.android.ui.components.screens.others.place_list.placeListScreen
import app.hdj.datepick.android.ui.components.screens.others.settings.settingsScreens
import app.hdj.datepick.android.ui.components.screens.others.splash.SplashScreen
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import app.hdj.datepick.android.ui.providers.LocalSnackBarPresenter
import app.hdj.datepick.android.ui.providers.SnackbarPresenter
import app.hdj.datepick.ui.components.DatePickScaffold

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DatePickApp() {

    val navController = rememberNavController()

    val scaffoldState = rememberScaffoldState()

    val coroutineScope = rememberCoroutineScope()

    CompositionLocalProvider(
        LocalAppNavController provides navController,
        LocalSnackBarPresenter provides SnackbarPresenter(coroutineScope, scaffoldState)
    ) {

        DatePickScaffold(
            scaffoldState = scaffoldState,
            bottomBar = { MainBottomNavigation() }
        ) {

            NavHost(
                navController = navController,
                startDestination = AppNavigationGraph.Main.route
            ) {

                /* Main Screens */
                mainScreens()

                /* Other Screens */
                placeScreen()
                courseScreen()
                placeListScreen()

                /* Setting Screens */
                settingsScreens()

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
