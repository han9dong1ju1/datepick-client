package app.hdj.datepick.ui.utils

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

abstract class NestedNavigationGraph(parentRoute: String, nestedRoute: String) :
    NavigationGraph(parentRoute) {
    override val route = "$parentRoute/$nestedRoute"
}

abstract class NavigationGraph(open val route: String)

@Composable
fun NavController.currentScreenRoute(): String? {
    return currentBackStackEntry?.destination?.route
}