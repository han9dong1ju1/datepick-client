@file:OptIn(ExperimentalMaterialNavigationApi::class)

package app.hdj.datepick.android.ui.screens.course.courseDetail

import android.graphics.drawable.Icon
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import app.hdj.datepick.android.ui.NavGraphs
import app.hdj.datepick.android.ui.destinations.*
import app.hdj.datepick.android.ui.navDestination
import app.hdj.datepick.android.ui.providers.LocalMe
import app.hdj.datepick.android.utils.extract
import app.hdj.datepick.android.utils.onSucceedComposable
import app.hdj.datepick.domain.model.course.Course
import app.hdj.datepick.presentation.coursedetail.CourseDetailScreenViewModel
import app.hdj.datepick.presentation.coursedetail.CourseDetailScreenViewModelDelegate
import app.hdj.datepick.ui.components.BottomNavigationProperty
import app.hdj.datepick.ui.components.InsetTopBar
import app.hdj.datepick.ui.components.NavigationGraphBottomNavigation
import app.hdj.datepick.ui.components.TopAppBarBackButton
import app.hdj.datepick.ui.utils.collectInLaunchedEffect
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
import com.ramcosta.composedestinations.navigation.dependency


private val courseNavigationRoutesWithIcon = listOf(
    BottomNavigationProperty(Icons.Rounded.Info, "정보", CourseDetailDiaryScreenDestination.route),
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
fun CourseDetailScreenForDeepLink(
    courseId: Long,
    navigator: DestinationsNavigator
) {
    val vm = hiltViewModel<CourseDetailScreenViewModel>()

    LaunchedEffect(true) {
        vm.event(CourseDetailScreenViewModelDelegate.Event.LoadCourse(courseId))
    }

    CourseDetailScreenContent(vm = vm)
}

@Composable
@Destination
fun CourseDetailScreen(
    course: Course,
    navigator: DestinationsNavigator
) {
    val vm = hiltViewModel<CourseDetailScreenViewModel>()

    LaunchedEffect(true) {
        vm.event(CourseDetailScreenViewModelDelegate.Event.SetCourse(course))
    }

    CourseDetailScreenContent(vm = vm)
}

@Composable
private fun CourseDetailScreenContent(
    vm: CourseDetailScreenViewModelDelegate
) {

    val (state, effect, event) = vm.extract()

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
                    actions = {
                        state.courseState.onSucceedComposable {
                            val myId = LocalMe.current?.id
                            if (it.user.id == myId) {
                                IconButton({
                                    event(CourseDetailScreenViewModelDelegate.Event.SetInEditMode(!state.inEditMode))
                                }) {
                                    Icon(if (state.inEditMode) Icons.Rounded.Done else Icons.Rounded.Edit, null)
                                }
                            } else {
                                IconButton({
                                    if (myId != null) {

                                    } else {
                                        // Login
                                    }
                                }) {
                                    Icon(
                                        if (it.isPicked) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder,
                                        null
                                    )
                                }
                            }
                        }
                    },
                    enableDivider = false
                )
            },
            bottomBar = {
                AnimatedVisibility(
                    state.isBottomNavigationShown,
                    enter = slideInVertically { it },
                    exit = slideOutVertically { it }
                ) {
                    NavigationGraphBottomNavigation(
                        list = courseNavigationRoutesWithIcon,
                        navController = navController,
                        currentRoute = currentDestination?.route.orEmpty()
                    )
                }
            }
        ) {
            DestinationsNavHost(
                navGraph = NavGraphs.courseScreenNavGraphRoot,
                navController = navController,
                dependenciesContainerBuilder = {
                    dependency(vm)
                }
            )
        }
    }

}