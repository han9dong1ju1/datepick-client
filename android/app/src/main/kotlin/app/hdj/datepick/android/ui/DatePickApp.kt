package app.hdj.datepick.android.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.plusAssign
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import app.hdj.datepick.android.ui.screens.AppNavigationGraph
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
    BottomNavigationProperty(Icons.Rounded.Book, "다이어리", AppNavigationGraph.Main.Diary),
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
)
@Composable
fun DatePickApp() {

    val navController = rememberAnimatedNavController()

    val bottomSheetNavigator = rememberBottomSheetNavigator()

    navController.navigatorProvider += bottomSheetNavigator

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val currentRoute = remember(navBackStackEntry?.destination?.route) {
        navBackStackEntry?.destination?.route
    }

    CompositionLocalProvider(
        LocalAppNavController provides navController
    ) {

        BaseScaffold(
        ) {

            ModalBottomSheetLayout(
                bottomSheetNavigator = bottomSheetNavigator,
                sheetBackgroundColor = Color.Unspecified
            ) {

                AnimatedNavHost(
                    modifier = Modifier.fillMaxSize(),
                    navController = navController,
                    startDestination = AppNavigationGraph.Splash.route,
                    enterTransition = { materialTransitionZaxisIn },
                    exitTransition = { materialTransitionZaxisOut }
                ) {

                }

            }

        }

    }

}
