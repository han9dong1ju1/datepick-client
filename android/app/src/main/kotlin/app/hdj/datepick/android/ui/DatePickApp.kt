package app.hdj.datepick.android.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Book
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Map
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import app.hdj.datepick.android.ui.destinations.HomeScreenDestination
import app.hdj.datepick.android.ui.destinations.MapScreenDestination
import app.hdj.datepick.android.ui.destinations.MyDateScreenDestination
import app.hdj.datepick.android.ui.destinations.ProfileScreenDestination
import app.hdj.datepick.android.ui.providers.DatePickComposableProviderScope
import app.hdj.datepick.android.ui.providers.WindowSize
import app.hdj.datepick.android.ui.providers.rememberWindowSizeClass
import app.hdj.datepick.android.utils.extract
import app.hdj.datepick.domain.settings.AppSettings
import app.hdj.datepick.presentation.DatePickAppViewModel
import app.hdj.datepick.presentation.DatePickAppViewModelDelegate
import app.hdj.datepick.ui.components.BaseScaffold
import app.hdj.datepick.ui.components.BottomNavigationProperty
import app.hdj.datepick.ui.components.NavigationGraphBottomNavigation
import app.hdj.datepick.ui.components.NavigationGraphNavigationRail
import app.hdj.datepick.ui.styles.BaseTheme
import app.hdj.datepick.ui.utils.collectInLaunchedEffect
import app.hdj.datepick.ui.utils.getActivity
import app.hdj.datepick.utils.location.LocationTracker
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine
import com.ramcosta.composedestinations.navigation.dependency


internal val mainNavigationRoutesWithIcon = listOf(
    BottomNavigationProperty(Icons.Rounded.Home, "홈", HomeScreenDestination.route),
    BottomNavigationProperty(Icons.Rounded.Map, "지도", MapScreenDestination.route),
    BottomNavigationProperty(Icons.Rounded.Book, "기록", MyDateScreenDestination.route),
    BottomNavigationProperty(Icons.Rounded.Person, "프로필", ProfileScreenDestination.route),
)

@OptIn(
    ExperimentalAnimationApi::class,
    ExperimentalMaterialNavigationApi::class,
)
@Composable
fun DatePickApp(
    appViewModel: DatePickAppViewModel,
    locationTracker: LocationTracker
) {
    DatePickComposableProviderScope(appViewModel, locationTracker) {

        val (state, effect) = appViewModel.extract()

        val isDarkTheme = when (state.appTheme) {
            AppSettings.AppTheme.Light -> false
            AppSettings.AppTheme.Dark -> true
            else -> isSystemInDarkTheme()
        }

        val systemUiController = rememberSystemUiController()

        val windowSize = rememberWindowSizeClass()
        val isMedium = windowSize == WindowSize.Medium
        val showNavigationRail = windowSize == WindowSize.Expanded || isMedium

        val bottomSheetNavigator = rememberBottomSheetNavigator()
        val navController = rememberAnimatedNavController(bottomSheetNavigator)
        val navHostEngine = rememberAnimatedNavHostEngine()

        val navBackStackEntry by navController.currentBackStackEntryAsState()

        val currentDestination = navBackStackEntry?.appDestination()

        effect.collectInLaunchedEffect {
            when (it) {
                DatePickAppViewModelDelegate.Effect.Error -> {

                }
                DatePickAppViewModelDelegate.Effect.UserBanned -> {

                }
            }
        }

        LaunchedEffect(isDarkTheme) {
            systemUiController.systemBarsDarkContentEnabled = !isDarkTheme
        }

        BaseTheme(isDarkTheme = isDarkTheme) {

            ModalBottomSheetLayout(
                bottomSheetNavigator = bottomSheetNavigator,
                sheetBackgroundColor = Color.Unspecified,
                scrimColor = if (MaterialTheme.colors.isLight) Color.Black.copy(alpha = 0.5f)
                else Color.Black.copy(alpha = 0.7f),
            ) {
                BaseScaffold(
                    bottomBar = {
                        if (!showNavigationRail) {
                            AnimatedVisibility(
                                listOf(
                                    HomeScreenDestination,
                                    MapScreenDestination,
                                    ProfileScreenDestination,
                                    MyDateScreenDestination
                                ).contains(currentDestination),
                                enter = slideInVertically { it },
                                exit = slideOutVertically { it }
                            ) {
                                NavigationGraphBottomNavigation(
                                    list = mainNavigationRoutesWithIcon,
                                    navController = navController,
                                    currentRoute = currentDestination?.route.orEmpty()
                                )
                            }
                        }
                    }
                ) {

                    Row(modifier = Modifier.fillMaxSize()) {

                        if (showNavigationRail) {
                            NavigationGraphNavigationRail(
                                list = mainNavigationRoutesWithIcon,
                                navController = navController,
                                currentRoute = currentDestination?.route.orEmpty()
                            )
                            Box(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .width(1.dp)
                                    .background(MaterialTheme.colors.onBackground.copy(alpha = 0.03f))
                            )
                        }

                        DestinationsNavHost(
                            navGraph = NavGraphs.root,
                            navController = navController,
                            engine = navHostEngine,
                            dependenciesContainerBuilder = {
                                dependency(appViewModel)
                            }
                        )

                    }
                }
            }

        }
    }

}
