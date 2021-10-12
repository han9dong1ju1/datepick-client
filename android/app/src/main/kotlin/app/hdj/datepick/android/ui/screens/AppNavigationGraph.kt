package app.hdj.datepick.android.ui.screens

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.*
import app.hdj.datepick.android.ui.screens.others.featuredDetail.FeaturedNavigationArgument
import app.hdj.datepick.android.ui.screens.others.image.ImagesScreenArgument
import app.hdj.datepick.android.ui.screens.others.placeDetail.PlaceNavigationArgument
import app.hdj.datepick.android.utils.datePickNavDeepLink
import app.hdj.datepick.android.utils.externalDatePickNavDeepLink
import app.hdj.datepick.domain.model.featured.Featured
import app.hdj.datepick.domain.model.place.Place
import app.hdj.datepick.ui.utils.NavigationGraph
import app.hdj.datepick.ui.utils.NestedNavigationGraph
import com.google.accompanist.navigation.animation.composable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

fun NavController.navigateRoute(navigationGraph: NavigationGraph) {
    navigate(navigationGraph.route)
}

fun NavController.openFeatured(featured: Featured) {
    navigateRoute(AppNavigationGraph.FeaturedDetail.graphWithArgument(featured))
}

fun NavController.openPlace(place: Place) {
    navigateRoute(AppNavigationGraph.PlaceDetail.graphWithArgument(place))
}

fun NavController.openWebUrl(url: String) {
    navigateRoute(AppNavigationGraph.Web.graphWithArgument(url))
}

fun <Graph : NavigationGraph> NavGraphBuilder.appNavigationComposable(
    graph: Graph,
    enterTransition: (
    AnimatedContentScope<String>.(initial: NavBackStackEntry, target: NavBackStackEntry) -> EnterTransition?
    )? = null,
    exitTransition: (
    AnimatedContentScope<String>.(initial: NavBackStackEntry, target: NavBackStackEntry) -> ExitTransition?
    )? = null,
    popEnterTransition: (
    AnimatedContentScope<String>.(initial: NavBackStackEntry, target: NavBackStackEntry) -> EnterTransition?
    )? = enterTransition,
    popExitTransition: (
    AnimatedContentScope<String>.(initial: NavBackStackEntry, target: NavBackStackEntry) -> ExitTransition?
    )? = exitTransition,
    content: @Composable AnimatedVisibilityScope.(NavBackStackEntry) -> Unit
) = composable(
    graph.route,
    graph.arguments,
    graph.deeplinks,
    enterTransition, exitTransition, popEnterTransition, popExitTransition, content
)

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

    object FeaturedDetail : AppNavigationGraph("featured/{featuredId}?featured={featured}") {
        const val ARGUMENT_FEATURED_ID = "featuredId"
        const val ARGUMENT_FEATURED = "featured"

        fun graphWithArgument(featured: Featured) =
            NavigationGraph(
                "featured/${featured.id}?featured=${
                    Json.encodeToString(FeaturedNavigationArgument.fromFeatured(featured))
                }"
            )

        override val arguments: List<NamedNavArgument> = listOf(
            navArgument(ARGUMENT_FEATURED_ID) { type = NavType.LongType },
            navArgument(ARGUMENT_FEATURED) {
                type = NavType.StringType
                nullable = true
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

        fun graphWithArgument(argument: ImagesScreenArgument) = NavigationGraph(
            "images?images=${Json.encodeToString(argument)}"
        )
    }

    object SearchPlace : AppNavigationGraph("search_place")

    object AppUpdateDialog : AppNavigationGraph("app_update")

    object LoginDialog : AppNavigationGraph("login_dialog")

    object CreateCourse : AppNavigationGraph("create_course/{courseId}") {
        const val ARGUMENT_COURSE_ID = "courseId"
    }

    object PlaceDetail : AppNavigationGraph("place/{placeId}?place={place}") {
        const val ARGUMENT_PLACE_ID = "placeId"
        const val ARGUMENT_PLACE = "place"

        fun graphWithArgument(place: Place) = NavigationGraph(
            "place/${place.id}?place=${Json.encodeToString(PlaceNavigationArgument.fromPlace(place))}"
        )

        override val arguments: List<NamedNavArgument> = listOf(
            navArgument(ARGUMENT_PLACE_ID) { type = NavType.LongType },
            navArgument(ARGUMENT_PLACE) {
                type = NavType.StringType
                nullable = true
            },
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

    object Course : AppNavigationGraph("course/{courseId}") {
        const val ARGUMENT_COURSE_ID = "courseId"
    }

    object UserProfileEdit : AppNavigationGraph("user_profile_edit")

    object CourseList : AppNavigationGraph("course_list?search={search}&sort={sort}") {
        const val ARGUMENT_SEARCH = "search"
        const val ARGUMENT_SORT = "sort"
    }

    sealed class Settings(nestedRoute: String) : NestedNavigationGraph(route, nestedRoute) {

        companion object {
            const val route = "settings"
        }

        object GeneralSettings : Settings("general")
        object NotificationSettings : Settings("notification")
    }

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
}