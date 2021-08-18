package app.hdj.datepick.ui.utils

import android.os.Parcelable
import androidx.compose.runtime.Composable
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import java.io.Serializable

abstract class NestedNavigationGraph(parentRoute: String, nestedRoute: String) :
    NavigationGraph(parentRoute) {
    override val route = "$parentRoute/$nestedRoute"
}

open class NavigationGraph(open val route: String)

@Composable
fun NavController.currentScreenRoute(): String? {
    return currentBackStackEntry?.destination?.route
}

@Suppress("UNCHECKED_CAST")
fun <T> NavController.getArgument(name: String): T? {
    return previousBackStackEntry?.arguments?.getParcelable(name) as? T
}

fun NavController.putArguments(vararg pairs: Pair<String, Parcelable?>) {
    currentBackStackEntry?.arguments = bundleOf(*pairs)
}