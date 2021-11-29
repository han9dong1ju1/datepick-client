package app.hdj.datepick.ui.utils

import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDeepLink
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

abstract class NestedNavigationGraph(parentRoute: String, nestedRoute: String) :
    NavigationGraph(parentRoute) {
    override val route = "$parentRoute/$nestedRoute"
}

open class NavigationGraph(open val route: String) {

    open val arguments: List<NamedNavArgument> = emptyList()
    open val deeplinks: List<NavDeepLink> = emptyList()

}

@Composable
fun NavController.currentScreenRoute(): String? {
    return currentBackStackEntry?.destination?.route
}

@Suppress("UNCHECKED_CAST")
inline fun <reified T> NavBackStackEntry.getJsonDataArgument(name: String): T? {
    val json = arguments?.getString(name) ?: return null
    return Json.decodeFromString<T>(json)
}