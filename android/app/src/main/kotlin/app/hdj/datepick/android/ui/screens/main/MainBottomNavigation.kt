package app.hdj.datepick.android.ui.screens.main

import androidx.compose.animation.*
import androidx.compose.runtime.Composable
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import app.hdj.datepick.ui.components.BottomNavigationProperty
import app.hdj.datepick.ui.components.NavigationGraphBottomNavigation

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainBottomNavigation(
    mainNavigationRoutesWithIcon : List<BottomNavigationProperty>,
    currentRoute : String?
) {

    val navController = LocalAppNavController.current

    val isRouteAllowedForBottomNavigation =
        mainNavigationRoutesWithIcon.map { it.navigation.route }.contains(currentRoute)

    AnimatedVisibility(
        visible = isRouteAllowedForBottomNavigation,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it })
    ) {
        NavigationGraphBottomNavigation(
            navController,
            mainNavigationRoutesWithIcon
        )
    }

}