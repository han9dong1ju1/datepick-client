package app.hdj.datepick.android.ui.components.screens

import androidx.navigation.NavController
import app.hdj.datepick.domain.model.featured.Featured
import app.hdj.datepick.ui.utils.NavigationGraph
import app.hdj.datepick.ui.utils.NestedNavigationGraph

val NavController.searchPlace : () -> Unit
    get() = {
        navigate(AppNavigationGraph.SearchPlace.route)
    }

val NavController.showSetting : () -> Unit
    get() = {
        navigate(AppNavigationGraph.Settings.route)
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
        fun route(featured: Featured) = "featured/${featured.id}"
    }

    object SearchPlace : AppNavigationGraph("search_place")

    object AppUpdateDialog : AppNavigationGraph("app_update")

    object LoginDialog : AppNavigationGraph("login_dialog")

    object CreateCourse : AppNavigationGraph("create_course/{courseId}") {
        const val ARGUMENT_COURSE_ID = "courseId"
//        fun route(courseMetadata: app.hdj.datepick.data.model.course.CourseMetadata) = "create_course/${courseMetadata.id}"
    }

    object Place : AppNavigationGraph("place/{placeId}") {
        const val ARGUMENT_PLACE_ID = "placeId"
//        fun route(place: Place) = "place/${place.id}"
    }

    object PlaceList : AppNavigationGraph("place_list?search={search}&sort={sort}") {
        const val ARGUMENT_SEARCH = "search"
        const val ARGUMENT_SORT = "sort"
//        fun route(placeQuery: app.hdj.datepick.domain.PlaceQuery) =
//            "place_list?$ARGUMENT_SEARCH=${placeQuery.search}&$ARGUMENT_SORT=${placeQuery.sort.value}"
    }

    object Course : AppNavigationGraph("course/{courseId}") {
        const val ARGUMENT_COURSE_ID = "courseId"
//        fun route(courseMetadata: app.hdj.datepick.data.model.course.CourseMetadata) = "course/${courseMetadata.id}"
    }

    object CourseList : AppNavigationGraph("course_list?search={search}&sort={sort}") {
        const val ARGUMENT_SEARCH = "search"
        const val ARGUMENT_SORT = "sort"
//        fun route(course: app.hdj.datepick.domain.CourseQuery) =
//            "course_list?$ARGUMENT_SEARCH=${course.search}&$ARGUMENT_SORT=${course.sort.value}"
    }

    sealed class Settings(nestedRoute: String) : NestedNavigationGraph(route, nestedRoute) {

        companion object {
            const val route = "settings"
        }

        object List : Settings("licenses")

        object Notifications : Settings("licenses")

        object Licenses : Settings("licenses")

    }

}