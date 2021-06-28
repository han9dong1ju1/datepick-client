package app.hdj.datepick.ui.navigation

sealed class NestedNavigationGraph(val parentRoute: String, nestedRoute: String) :
    NavigationGraph(parentRoute) {
    override val route = "$parentRoute/$nestedRoute"
}

sealed class NavigationGraph(open val route: String) {

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

    object Place : NavigationGraph("place/{placeId}") {
        const val ARGUMENT_ID = "placeId"
        fun routeWithId(id: Long) = "place/$id"
    }

    object Course : NavigationGraph("course/{courseId}") {
        const val ARGUMENT_ID = "courseId"
        fun routeWithId(id: Long) = "course/$id"
    }

    object Settings : NavigationGraph("settings")

}