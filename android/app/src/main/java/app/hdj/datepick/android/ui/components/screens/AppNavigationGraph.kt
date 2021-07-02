package app.hdj.datepick.android.ui.components.screens

sealed class NestedNavigationGraph(parentRoute: String, nestedRoute: String) :
    AppNavigationGraph(parentRoute) {
    override val route = "$parentRoute/$nestedRoute"
}

sealed class AppNavigationGraph(open val route: String) {

    /* Main Start */
    sealed class Main(nestedRoute: String) : NestedNavigationGraph(route, nestedRoute) {

        companion object {
            const val route = "main"
        }

        object Home : Main("home")

        object Pick : Main("pick")

        object Map : Main("map")

        object Profile : Main("profile")

    }
    /* Main End */

    object Place : AppNavigationGraph("place/{placeId}") {
        const val ARGUMENT_ID = "placeId"
        fun routeWithId(id: Long) = "place/$id"
    }

    object Course : AppNavigationGraph("course/{courseId}") {
        const val ARGUMENT_ID = "courseId"
        fun routeWithId(id: Long) = "course/$id"
    }

    object Settings : AppNavigationGraph("settings")

}