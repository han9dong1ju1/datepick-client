package app.hdj.datepick.android.ui.components.screens

import androidx.navigation.NavController
import app.hdj.datepick.ui.utils.NavigationGraph
import app.hdj.datepick.ui.utils.NestedNavigationGraph
import app.hdj.shared.client.domain.CourseQuery
import app.hdj.shared.client.domain.PlaceQuery
import app.hdj.shared.client.domain.entity.CourseMetadata
import app.hdj.shared.client.domain.entity.Place

val NavController.searchPlace : () -> Unit
    get() = {
        navigate(AppNavigationGraph.SearchPlace.route)
    }

val NavController.showSetting : () -> Unit
    get() = {
        navigate(AppNavigationGraph.Settings.route)
    }

val NavController.showPlace
    get() = { place: Place ->
        navigate(AppNavigationGraph.Place.route(place))
    }

val NavController.showCourse
    get() = { courseMetadata: CourseMetadata ->
        navigate(AppNavigationGraph.Course.route(courseMetadata))
    }

val NavController.showPlaceList
    get() = { query: PlaceQuery ->
        navigate(AppNavigationGraph.PlaceList.route(query))
    }

val NavController.showCourseList
    get() = { query: CourseQuery ->
        navigate(AppNavigationGraph.CourseList.route(query))
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

    object SearchPlace : AppNavigationGraph("search_place")

    object AppUpdateDialog : AppNavigationGraph("app_update")

    object CreateCourse : AppNavigationGraph("create_course/{courseId}") {
        const val ARGUMENT_COURSE_ID = "courseId"
        fun route(courseMetadata: app.hdj.shared.client.domain.entity.CourseMetadata) = "create_course/${courseMetadata.id}"
    }

    object Place : AppNavigationGraph("place/{placeId}") {
        const val ARGUMENT_PLACE_ID = "placeId"
        fun route(place: app.hdj.shared.client.domain.entity.Place) = "place/${place.id}"
    }

    object PlaceList : AppNavigationGraph("place_list?search={search}&sort={sort}") {
        const val ARGUMENT_SEARCH = "search"
        const val ARGUMENT_SORT = "sort"
        fun route(placeQuery: PlaceQuery) =
            "place_list?$ARGUMENT_SEARCH=${placeQuery.search}&$ARGUMENT_SORT=${placeQuery.sort.value}"
    }

    object Course : AppNavigationGraph("course/{courseId}") {
        const val ARGUMENT_COURSE_ID = "courseId"
        fun route(courseMetadata: app.hdj.shared.client.domain.entity.CourseMetadata) = "course/${courseMetadata.id}"
    }

    object CourseList : AppNavigationGraph("course_list?search={search}&sort={sort}") {
        const val ARGUMENT_SEARCH = "search"
        const val ARGUMENT_SORT = "sort"
        fun route(course: CourseQuery) =
            "course_list?$ARGUMENT_SEARCH=${course.search}&$ARGUMENT_SORT=${course.sort.value}"
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