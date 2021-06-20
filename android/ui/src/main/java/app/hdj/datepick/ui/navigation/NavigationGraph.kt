package app.hdj.datepick.ui.navigation

sealed class NavigationGraph(val route: String) {

    object Login : NavigationGraph("login")

    /* Main Start */
    object Home : NavigationGraph("home")

    object Pick : NavigationGraph("pick")

    object Map : NavigationGraph("map")

    object Profile : NavigationGraph("profile")
    /* Main End */

    object Place : NavigationGraph("place/{placeId}") {
        const val ARGUMENT_ID = "placeId"
        fun routeWithId(id : Long) = "place/$id"
    }

    object Course : NavigationGraph("course/{courseId}") {
        const val ARGUMENT_ID = "courseId"
        fun routeWithId(id : Long) = "course/$id"
    }

    object Settings : NavigationGraph("settings")

}