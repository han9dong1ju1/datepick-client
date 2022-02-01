package app.hdj.datepick.android.ui.screens

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.dialog
import app.hdj.datepick.android.utils.datePickNavDeepLink
import app.hdj.datepick.android.utils.externalDatePickNavDeepLink
import app.hdj.datepick.domain.model.diary.Diary
import app.hdj.datepick.domain.model.featured.Featured
import app.hdj.datepick.domain.model.place.Place
import app.hdj.datepick.ui.utils.DialogGraph
import app.hdj.datepick.ui.utils.NavigationGraph
import app.hdj.datepick.ui.utils.NestedNavigationGraph
import app.hdj.datepick.ui.utils.ScreenGraph
import com.google.accompanist.navigation.animation.composable

fun NavController.navigateRoute(
    navigationGraph: NavigationGraph,
    builder: NavOptionsBuilder.() -> Unit = {}
) {
    navigate(navigationGraph.route, builder)
}

fun NavController.openFeatured(
    featured: Featured,
    builder: NavOptionsBuilder.() -> Unit = {}
) {
    navigateRoute(AppNavigationGraph.FeaturedDetail.graphWithArgument(featured), builder)
}

fun NavController.openPlace(
    place: Place,
    builder: NavOptionsBuilder.() -> Unit = {}
) {
    navigateRoute(AppNavigationGraph.PlaceDetail.graphWithArgument(place), builder)
}

fun NavController.openWebUrl(
    url: String,
    builder: NavOptionsBuilder.() -> Unit = {}
) {
    navigateRoute(AppNavigationGraph.Web.graphWithArgument(url), builder)
}

fun <Graph : NavigationGraph> NavGraphBuilder.appNavigationComposable(
    graph: Graph,
    enterTransition: (
    AnimatedContentScope<NavBackStackEntry>.() -> EnterTransition?
    )? = null,
    exitTransition: (
    AnimatedContentScope<NavBackStackEntry>.() -> ExitTransition?
    )? = null,
    popEnterTransition: (
    AnimatedContentScope<NavBackStackEntry>.() -> EnterTransition?
    )? = enterTransition,
    popExitTransition: (
    AnimatedContentScope<NavBackStackEntry>.() -> ExitTransition?
    )? = exitTransition,
    content: @Composable AnimatedVisibilityScope.(NavBackStackEntry) -> Unit
) = composable(
    graph.route,
    graph.arguments,
    graph.deeplinks,
    enterTransition, exitTransition, popEnterTransition, popExitTransition, content
)

sealed class AppNavigationGraph(override val route: String) : ScreenGraph(route) {

    /* Main Start */
    sealed class Main(nestedRoute: String) : NestedNavigationGraph(route, nestedRoute) {

        companion object : AppNavigationGraph("main")

        object Home : Main("home")
        object Pick : Main("pick")
        object Map : Main("map")
        object Profile : Main("profile")
        object MyDate : Main("diary")

    }
    /* Main End */

    object FeaturedDetail : AppNavigationGraph("featured/{featuredId}") {
        const val ARGUMENT_FEATURED_ID = "featuredId"

        fun graphWithArgument(featured: Featured) =
            NavigationGraph("featured/${featured.id}")

        override val arguments = listOf(
            navArgument(ARGUMENT_FEATURED_ID) {
                type = NavType.LongType
            }
        )

        override val deeplinks: List<NavDeepLink> = listOf(
            datePickNavDeepLink(route),
            externalDatePickNavDeepLink(route)
        )

    }

    object Images : AppNavigationGraph("images?images={images}") {
        const val ARGUMENT_IMAGES = "images"

        override val arguments = listOf(
            navArgument(ARGUMENT_IMAGES) {
                type = NavType.StringType
                nullable = true
            }
        )
    }

    object LocationPermissionDeniedDialog : DialogGraph("location_permission_denied_dialog")
    object ExitDialog : DialogGraph("exit_dialog")
    object SearchPlace : DialogGraph("search_place")
    object AppUpdateDialog : DialogGraph("app_update")
    object LoginDialog : DialogGraph("login_dialog")

    sealed class CreateCourse(nestedRoute: String) : NestedNavigationGraph(route, nestedRoute) {

        companion object : AppNavigationGraph("create_course")

        object CourseTheme : CreateCourse("course_theme")
        object Info : CreateCourse("info")

    }

    object PlaceDetail : AppNavigationGraph("place/{placeId}") {

        const val ARGUMENT_PLACE_ID = "placeId"

        fun graphWithArgument(place: Place) = NavigationGraph("place/${place.id}")

        override val arguments: List<NamedNavArgument> = listOf(
            navArgument(ARGUMENT_PLACE_ID) { type = NavType.LongType }
        )

        override val deeplinks: List<NavDeepLink> = listOf(
            datePickNavDeepLink(route),
            externalDatePickNavDeepLink(route)
        )

    }

    object PlaceList : AppNavigationGraph("place_list?search={search}&sort={sort}") {
        const val ARGUMENT_SEARCH = "search"
        const val ARGUMENT_SORT = "sort"
    }

    object CourseDetail : AppNavigationGraph("course/{courseId}") {
        const val ARGUMENT_COURSE_ID = "courseId"

        fun graphWithArgument(
            course: app.hdj.datepick.domain.model.course.Course
        ) = NavigationGraph("course/${course.id}")

        override val arguments = listOf(
            navArgument(ARGUMENT_COURSE_ID) {
                type = NavType.LongType
            }
        )

    }

    object Notifications : AppNavigationGraph("notifications")

    object UserProfileEdit : AppNavigationGraph("user_profile_edit")

    object CourseList : AppNavigationGraph("course_list?search={search}&sort={sort}") {
        const val ARGUMENT_SEARCH = "search"
        const val ARGUMENT_SORT = "sort"
    }

    sealed class AppSettings(nestedRoute: String) : NestedNavigationGraph(route, nestedRoute) {

        companion object : AppNavigationGraph("settings")

        object List : AppSettings("general")

        object AppThemeDialog : AppSettings("app_theme_dialog")
    }

    object NotificationSettings : AppNavigationGraph("notification")

    object Web : AppNavigationGraph("web?url={url}") {
        const val ARGUMENT_URL = "url"

        fun graphWithArgument(url: String) = NavigationGraph("web?url=$url")

        override val arguments: List<NamedNavArgument> = listOf(
            navArgument(ARGUMENT_URL) { type = NavType.StringType }
        )

        override val deeplinks: List<NavDeepLink> = listOf(
            datePickNavDeepLink(route),
            externalDatePickNavDeepLink(route)
        )

    }


    object DiaryDetail : AppNavigationGraph("diary/{diaryId}") {
        const val ARGUMENT_DIARY_ID = "diaryId"

        fun graphWithArgument(diary: Diary) =
            NavigationGraph("diary/${diary.id}")

        override val arguments: List<NamedNavArgument> = listOf(
            navArgument(ARGUMENT_DIARY_ID) { type = NavType.LongType }
        )

        override val deeplinks: List<NavDeepLink> = listOf(
            datePickNavDeepLink(route),
            externalDatePickNavDeepLink(route)
        )

    }

    object Splash : AppNavigationGraph("splash")


    object NetworkErrorDialog : AppNavigationGraph("network_error_dialog")

}

fun NavGraphBuilder.navGraphBuildScope(block: NavGraphBuilderScope.() -> Unit) {
    val scope = NavGraphBuilderScope(this)
    scope.block()
}

class NavGraphBuilderScope(private val navGraphBuilder: NavGraphBuilder) {

    operator fun ScreenGraph.invoke(content: @Composable AnimatedVisibilityScope.(NavBackStackEntry) -> Unit) {
        navGraphBuilder.appNavigationComposable(this, content = content)
    }

    operator fun DialogGraph.invoke(content: @Composable (NavBackStackEntry) -> Unit) {
        navGraphBuilder.dialog(route, content = content)
    }

    fun nestedNavigation(
        startDestination: NavigationGraph,
        route: NavigationGraph,
        scope: NavGraphBuilderScope.() -> Unit
    ) {
        navGraphBuilder.navigation(
            startDestination.route,
            route.route
        ) {
            navGraphBuildScope(scope)
        }
    }

}