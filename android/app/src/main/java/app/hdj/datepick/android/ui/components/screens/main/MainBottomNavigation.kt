package app.hdj.datepick.android.ui.components.screens.main

import androidx.compose.animation.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Map
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.currentBackStackEntryAsState
import app.hdj.datepick.android.ui.components.screens.AppNavigationGraph
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import app.hdj.datepick.ui.components.BottomNavigationProperty
import app.hdj.datepick.ui.components.NavigationGraphBottomNavigation

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainBottomNavigation() {

    val navController = LocalAppNavController.current
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val mainNavigationRoutesWithIcon = listOf(
        BottomNavigationProperty(Icons.Rounded.Home, "홈", AppNavigationGraph.Main.Home),
        BottomNavigationProperty(Icons.Rounded.Map, "지도", AppNavigationGraph.Main.Map),
        BottomNavigationProperty(Icons.Rounded.Favorite, "PICK", AppNavigationGraph.Main.Pick),
        BottomNavigationProperty(Icons.Rounded.Person, "프로필", AppNavigationGraph.Main.Profile),
    )

    val currentRoute = navBackStackEntry?.destination?.route

    val isRouteAllowedForBottomNavigation =
        mainNavigationRoutesWithIcon.map { it.navigation.route }.contains(currentRoute)

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