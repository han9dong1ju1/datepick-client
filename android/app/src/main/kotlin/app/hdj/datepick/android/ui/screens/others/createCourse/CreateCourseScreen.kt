package app.hdj.datepick.android.ui.screens.others.createCourse

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import app.hdj.datepick.android.ui.screens.AppNavigationGraph.CreateCourse.*
import app.hdj.datepick.android.ui.screens.appNavigationComposable
import app.hdj.datepick.android.ui.screens.navigateRoute
import app.hdj.datepick.android.ui.screens.others.createCourse.info.CreateCourseInfoScreen
import app.hdj.datepick.android.ui.screens.others.createCourse.info.CreateCourseSelectedPlacesDialog
import app.hdj.datepick.android.ui.screens.others.createCourse.recommendedPlaces.CreateCourseRecommendedPlacesScreen
import app.hdj.datepick.android.ui.screens.others.createCourse.tags.CreateCourseTagsScreen
import app.hdj.datepick.ui.animation.materialTransitionXaxisIn
import app.hdj.datepick.ui.animation.materialTransitionXaxisOut
import app.hdj.datepick.ui.components.*
import app.hdj.datepick.ui.utils.*
import app.hdj.datepick.utils.PlatformLogger
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.material.BottomSheetNavigator
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.bottomSheet
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator

@OptIn(
    ExperimentalMaterial3Api::class,
    com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi::class
)
@Composable
fun CreateCourseScreen(
    vm: CreateCourseViewModelDelegate = hiltViewModel<CreateCourseViewModel>()
) {

    val (state, effect, event) = vm.extract()

    val scrollBehavior = remember {
        TopAppBarDefaults.pinnedScrollBehavior()
    }

    val appNavController = LocalAppNavController.current
    val bottomSheetNavigator = rememberBottomSheetNavigator()
    val createdCourseNavController = rememberAnimatedNavController(bottomSheetNavigator)

    val navBackStackEntry by createdCourseNavController.currentBackStackEntryAsState()

    val currentRoute = remember(navBackStackEntry?.destination?.route) {
        navBackStackEntry?.destination?.route
    }

    ModalBottomSheetLayout(
        bottomSheetNavigator = bottomSheetNavigator,
        sheetBackgroundColor = Color.Unspecified
    ) {
        BaseScaffold(
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                InsetSmallTopAppBar(
                    navigationIcon = { TopAppBarBackButton() },
                    title = {},
                    actions = {

                    },
                    scrollBehavior = scrollBehavior
                )
            }
        ) {

            ConstraintLayout(
                modifier = Modifier.fillMaxSize()
            ) {

                val (navHostRef, bottomButtonRef) = createRefs()

                AnimatedNavHost(
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(
                            navHostRef,
                            t2t() + b2t(bottomButtonRef) + fillHeightToConstraint
                        ),
                    navController = createdCourseNavController,
                    startDestination = Tags.route,
                    enterTransition = {
                        val initialRoute = initialState.destination.route
                        val targetRoute = targetState.destination.route
                        val slideToRight =
                            (initialRoute == RecommendedPlaces.route && targetRoute == Tags.route) ||
                                    (initialRoute == Info.route)

                        materialTransitionXaxisIn(!slideToRight)
                    },
                    exitTransition = {
                        val initialRoute = initialState.destination.route
                        val targetRoute = targetState.destination.route
                        val slideToRight =
                            (initialRoute == RecommendedPlaces.route && targetRoute == Tags.route) ||
                                    (initialRoute == Info.route)

                        materialTransitionXaxisOut(!slideToRight)
                    }
                ) {

                    appNavigationComposable(Tags) {
                        CreateCourseTagsScreen(state = state, event = event)
                    }

                    appNavigationComposable(RecommendedPlaces) {
                        CreateCourseRecommendedPlacesScreen(
                            createdCourseNavController,
                            state = state, event = event
                        )
                    }

                    appNavigationComposable(Info) {
                        CreateCourseInfoScreen(state = state, event = event)
                    }

                    bottomSheet(ShowSelectedPlaces.route) {
                        val state by vm.state.collectAsState()
                        CreateCourseSelectedPlacesDialog(state = state, event = event)
                    }

                }

                Box(
                    Modifier
                        .navigationBarsPadding()
                        .fillMaxWidth()
                        .constrainAs(bottomButtonRef, b2b())
                ) {
                    BaseButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        text = if (currentRoute == Info.route) "저장하기" else "다음으로"
                    ) {
                        when (currentRoute) {
                            Tags.route ->
                                createdCourseNavController.navigateRoute(RecommendedPlaces)
                            RecommendedPlaces.route ->
                                createdCourseNavController.navigateRoute(Info)
                            Info.route -> {

                            }
                        }
                    }
                }

            }

        }
    }

}