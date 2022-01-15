package app.hdj.datepick.android.ui.screens.others.createCourse

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import app.hdj.datepick.android.ui.screens.AppNavigationGraph.CreateCourse.*
import app.hdj.datepick.android.ui.screens.appNavigationComposable
import app.hdj.datepick.android.ui.screens.navigateRoute
import app.hdj.datepick.android.ui.screens.others.createCourse.info.CreateCourseInfoScreen
import app.hdj.datepick.android.ui.screens.others.createCourse.info.CreateCourseSelectedPlacesDialog
import app.hdj.datepick.android.ui.screens.others.createCourse.tags.CreateCourseTagsScreen
import app.hdj.datepick.ui.animation.materialTransitionXaxisIn
import app.hdj.datepick.ui.animation.materialTransitionXaxisOut
import app.hdj.datepick.ui.components.BaseScaffold
import app.hdj.datepick.ui.components.TopAppBarBackButton
import com.google.accompanist.insets.ui.TopAppBar
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.bottomSheet
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator

@OptIn(
    com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi::class
)
@Composable
fun CreateCourseScreen(
    vm: CreateCourseViewModelDelegate = hiltViewModel<CreateCourseViewModel>()
) {

    val appNavController = LocalAppNavController.current
    val bottomSheetNavigator = rememberBottomSheetNavigator()
    val createdCourseNavController = rememberAnimatedNavController(bottomSheetNavigator)

    val navBackStackEntry by createdCourseNavController.currentBackStackEntryAsState()

    ModalBottomSheetLayout(
        bottomSheetNavigator = bottomSheetNavigator,
        sheetBackgroundColor = Color.Unspecified,
        scrimColor = Color.Black.copy(0.5f)
    ) {
        BaseScaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    navigationIcon = { TopAppBarBackButton() },
                    title = {}
                )
            }
        ) {
            AnimatedNavHost(
                modifier = Modifier
                    .fillMaxSize(),
                navController = createdCourseNavController,
                startDestination = Tags.route,
                enterTransition = {
                    val initialRoute = initialState.destination.route
                    val targetRoute = targetState.destination.route
                    val slideToRight =
                        (initialRoute == Info.route && targetRoute == Tags.route) ||
                                (initialRoute == Info.route)

                    materialTransitionXaxisIn(!slideToRight)
                },
                exitTransition = {
                    val initialRoute = initialState.destination.route
                    val targetRoute = targetState.destination.route
                    val slideToRight =
                        (initialRoute == Info.route && targetRoute == Tags.route) ||
                                (initialRoute == Info.route)

                    materialTransitionXaxisOut(!slideToRight)
                }
            ) {

                appNavigationComposable(Tags) {
                    CreateCourseTagsScreen(vm) {
                        createdCourseNavController.navigateRoute(Info)
                    }
                }

                appNavigationComposable(Info) {
                    CreateCourseInfoScreen(vm) {
                        appNavController.popBackStack()
                        appNavController.navigate("course/0")
                    }
                }

                bottomSheet(ShowSelectedPlaces.route) {
                    CreateCourseSelectedPlacesDialog(vm)
                }

            }

        }
    }

}