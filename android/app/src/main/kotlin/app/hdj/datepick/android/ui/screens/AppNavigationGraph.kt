package app.hdj.datepick.android.ui.screens

import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.navArgument
import androidx.navigation.navDeepLink
import androidx.navigation.navOptions
import app.hdj.datepick.android.ui.screens.AppNavigationGraph.FeaturedDetail.ARGUMENT_FEATURED
import app.hdj.datepick.android.ui.screens.AppNavigationGraph.PlaceDetail.ARGUMENT_PLACE
import app.hdj.datepick.android.ui.screens.others.featuredDetail.FeaturedNavigationArgument
import app.hdj.datepick.android.ui.screens.others.placeDetail.PlaceNavigationArgument
import app.hdj.datepick.android.utils.datePickNavDeepLink
import app.hdj.datepick.android.utils.externalDatePickNavDeepLink
import app.hdj.datepick.domain.model.featured.Featured
import app.hdj.datepick.domain.model.place.Place
import app.hdj.datepick.ui.utils.NavigationGraph
import app.hdj.datepick.ui.utils.NestedNavigationGraph
import app.hdj.datepick.ui.utils.putArguments

fun NavController.navigateRoute(navigationGraph: NavigationGraph) {
    navigate(navigationGraph.route)
}

fun NavController.openFeatured(featured: Featured) {
    putArguments(ARGUMENT_FEATURED to FeaturedNavigationArgument.fromFeatured(featured))
    navigateRoute(AppNavigationGraph.FeaturedDetail)
}

fun NavController.openPlace(place: Place) {
    putArguments(ARGUMENT_PLACE to PlaceNavigationArgument.fromPlace(place))
    navigateRoute(AppNavigationGraph.PlaceDetail)
}

sealed class AppNavigationGraph(override val route: String) : NavigationGraph(route) {

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

    object FeaturedDetail : AppNavigationGraph("featured/{featuredId}") {
        const val ARGUMENT_FEATURED_ID = "featuredId"
        const val ARGUMENT_FEATURED = "featured"

        fun argument() = listOf(
            navArgument(ARGUMENT_FEATURED_ID) {
                type = NavType.LongType
            }
        )

        fun deeplink() = listOf(
            datePickNavDeepLink(route),
            externalDatePickNavDeepLink(route)
        )

    }

    object SearchPlace : AppNavigationGraph("search_place")

    object AppUpdateDialog : AppNavigationGraph("app_update")

    object LoginDialog : AppNavigationGraph("login_dialog")

    object CreateCourse : AppNavigationGraph("create_course/{courseId}") {
        const val ARGUMENT_COURSE_ID = "courseId"
    }

    object PlaceDetail : AppNavigationGraph("place/{placeId}") {
        const val ARGUMENT_PLACE_ID = "placeId"
        const val ARGUMENT_PLACE = "place"

        fun argument() = listOf(
            navArgument(ARGUMENT_PLACE_ID) {
                type = NavType.LongType
            }
        )

        fun deeplink() = listOf(
            datePickNavDeepLink(route),
            externalDatePickNavDeepLink(route)
        )

    }

    object PlaceList : AppNavigationGraph("place_list?search={search}&sort={sort}") {
        const val ARGUMENT_SEARCH = "search"
        const val ARGUMENT_SORT = "sort"
    }

    object Course : AppNavigationGraph("course/{courseId}") {
        const val ARGUMENT_COURSE_ID = "courseId"
    }

    object CourseList : AppNavigationGraph("course_list?search={search}&sort={sort}") {
        const val ARGUMENT_SEARCH = "search"
        const val ARGUMENT_SORT = "sort"
    }

    sealed class Settings(nestedRoute: String) : NestedNavigationGraph(route, nestedRoute) {

        companion object {
            const val route = "settings"
        }

        object List : Settings("licenses")
        object Notifications : Settings("licenses")
    }

}