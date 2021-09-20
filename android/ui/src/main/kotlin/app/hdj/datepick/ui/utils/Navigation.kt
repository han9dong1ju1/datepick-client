package app.hdj.datepick.ui.utils

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavDeepLink
import androidx.navigation.compose.NamedNavArgument

abstract class NestedNavigationGraph(parentRoute: String, nestedRoute: String) :
    NavigationGraph(parentRoute) {
    override val route = "$parentRoute/$nestedRoute"
}

open class NavigationGraph(open val route: String, open val argument : Bundle? = null) {

    open val arguments : List<NamedNavArgument> = emptyList()
    open val deeplinks : List<NavDeepLink> = emptyList()

}

@Composable
fun NavController.currentScreenRoute(): String? {
    return currentBackStackEntry?.destination?.route
}

@Suppress("UNCHECKED_CAST")
fun <T> NavController.getArgument(name: String): T? {
    return previousBackStackEntry?.arguments?.getParcelable(name) as? T
}

fun NavController.putArguments(bundle: Bundle) {
    currentBackStackEntry?.arguments?.clear()
    currentBackStackEntry?.arguments?.putAll(bundle)
}