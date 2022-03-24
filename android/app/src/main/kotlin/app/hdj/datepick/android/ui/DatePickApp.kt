package app.hdj.datepick.android.ui

import androidx.compose.animation.*
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Book
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Map
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.currentBackStackEntryAsState
import app.hdj.datepick.android.ui.destinations.HomeScreenDestination
import app.hdj.datepick.android.ui.destinations.MapScreenDestination
import app.hdj.datepick.android.ui.destinations.MyDateScreenDestination
import app.hdj.datepick.android.ui.destinations.ProfileScreenDestination
import app.hdj.datepick.android.ui.providers.DatePickComposableProviderScope
import app.hdj.datepick.android.ui.providers.LocalDatePickAppViewModel
import app.hdj.datepick.android.utils.extract
import app.hdj.datepick.domain.settings.AppSettings
import app.hdj.datepick.presentation.DatePickAppViewModel
import app.hdj.datepick.ui.animation.materialTransitionSpecYaxisPopFromBottom
import app.hdj.datepick.ui.animation.materialTransitionXaxisIn
import app.hdj.datepick.ui.animation.materialTransitionYaxisIn
import app.hdj.datepick.ui.animation.materialTransitionYaxisOut
import app.hdj.datepick.ui.components.BaseScaffold
import app.hdj.datepick.ui.components.BottomNavigationProperty
import app.hdj.datepick.ui.components.NavigationGraphBottomNavigation
import app.hdj.datepick.ui.styles.BaseTheme
import app.hdj.datepick.utils.location.LocationTracker
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ramcosta.composedestinations.DestinationsNavHost


private val mainNavigationRoutesWithIcon = listOf(
    BottomNavigationProperty(Icons.Rounded.Home, "홈", HomeScreenDestination.route),
    BottomNavigationProperty(Icons.Rounded.Map, "지도", MapScreenDestination.route),
    BottomNavigationProperty(Icons.Rounded.Book, "내 데이트", MyDateScreenDestination.route),
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

        val (state) = appViewModel.extract()

        val isDarkTheme = when (state.appTheme) {
            AppSettings.AppTheme.Light -> false
            AppSettings.AppTheme.Dark -> true
            else -> isSystemInDarkTheme()
        }

        val systemUiController = rememberSystemUiController()

        LaunchedEffect(isDarkTheme) {
            systemUiController.systemBarsDarkContentEnabled = !isDarkTheme
        }

        BaseTheme(isDarkTheme = isDarkTheme) {

            val bottomSheetNavigator = rememberBottomSheetNavigator()
            val navController = rememberAnimatedNavController(bottomSheetNavigator)

            val navBackStackEntry by navController.currentBackStackEntryAsState()

            val currentDestination = navBackStackEntry?.navDestination

            ModalBottomSheetLayout(
                bottomSheetNavigator = bottomSheetNavigator,
                sheetBackgroundColor = Color.Unspecified
            ) {
                BaseScaffold(
                    bottomBar = {
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
                ) {
                    DestinationsNavHost(
                        navGraph = NavGraphs.root,
                        navController = navController
                    )
                }
            }

        }
    }

}
