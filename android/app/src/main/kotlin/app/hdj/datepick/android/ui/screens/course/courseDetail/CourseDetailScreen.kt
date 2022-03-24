@file:OptIn(ExperimentalMaterialNavigationApi::class)

package app.hdj.datepick.android.ui.screens.course.courseDetail

import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.currentBackStackEntryAsState
import app.hdj.datepick.android.ui.NavGraphs
import app.hdj.datepick.android.ui.destinations.*
import app.hdj.datepick.android.ui.navDestination
import app.hdj.datepick.android.ui.navtype.CourseNavType
import app.hdj.datepick.domain.model.course.Course
import app.hdj.datepick.ui.components.BottomNavigationProperty
import app.hdj.datepick.ui.components.InsetTopBar
import app.hdj.datepick.ui.components.NavigationGraphBottomNavigation
import app.hdj.datepick.ui.components.TopAppBarBackButton
import app.hdj.datepick.utils.DEEPLINK_URL
import app.hdj.datepick.utils.EXTERNAL_DEEPLINK_URL
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.DeepLink
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


private val courseNavigationRoutesWithIcon = listOf(
    BottomNavigationProperty(Icons.Rounded.Info, "정보", CourseDetailCoverScreenDestination.route),
    BottomNavigationProperty(Icons.Rounded.Place, "코스보기", CourseDetailPlacesScreenDestination.route),
    BottomNavigationProperty(Icons.Rounded.Comment, "댓글", CourseDetailCommentScreenDestination.route),
    BottomNavigationProperty(Icons.Rounded.Map, "지도보기", CourseDetailMapScreenDestination.route),
)

const val COURSE_SCREEN_NAV_GRAPH = "course_screen_nav_graph_root"

@Composable
@Destination(
    deepLinks = [
        DeepLink(uriPattern = "$DEEPLINK_URL/course/{courseId}"),
        DeepLink(uriPattern = "$EXTERNAL_DEEPLINK_URL/course/{courseId}"),
    ]
)
fun CourseScreenForDeepLink(
    courseId: Long,
    navigator: DestinationsNavigator
) {
    CourseDetailScreenContent(courseId)
}

@Composable
@Destination
fun CourseDetailScreen(
    course: Course,
    navigator: DestinationsNavigator
) {
    CourseDetailScreenContent(
        course = course
    )
}

@Composable
private fun CourseDetailScreenContent(
    courseId: Long? = null,
    course: Course? = null
) {

    val bottomSheetNavigator = rememberBottomSheetNavigator()
    val navController = rememberAnimatedNavController(bottomSheetNavigator)

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val currentDestination = navBackStackEntry?.navDestination

    ModalBottomSheetLayout(
        bottomSheetNavigator = bottomSheetNavigator,
        sheetBackgroundColor = Color.Unspecified
    ) {
        Scaffold(
            topBar = {
                InsetTopBar(
                    {},
                    navigationIcon = { TopAppBarBackButton() },
                )
            },
            bottomBar = {
                NavigationGraphBottomNavigation(
                    list = courseNavigationRoutesWithIcon,
                    navController = navController,
                    currentRoute = currentDestination?.route.orEmpty()
                )
            }
        ) {
            DestinationsNavHost(
                navGraph = NavGraphs.courseScreenNavGraphRoot,
                navController = navController,
                dependenciesContainerBuilder = {

                }
            )
        }
    }

}