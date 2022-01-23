package app.hdj.datepick.android.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Book
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Map
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.fragment.app.FragmentManager
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.plusAssign
import app.hdj.datepick.android.ui.providers.DatePickComposableProviderScope
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import app.hdj.datepick.android.ui.providers.LocalDatePickAppViewModel
import app.hdj.datepick.android.ui.providers.LocalFragmentManager
import app.hdj.datepick.android.ui.screens.AppNavigationGraph
import app.hdj.datepick.android.ui.screens.DatepickScreenNavHost
import app.hdj.datepick.android.utils.extract
import app.hdj.datepick.domain.settings.AppSettings
import app.hdj.datepick.presentation.DatePickAppViewModel
import app.hdj.datepick.ui.components.BaseScaffold
import app.hdj.datepick.ui.components.BottomNavigationProperty
import app.hdj.datepick.ui.components.NavigationGraphBottomNavigation
import app.hdj.datepick.ui.styles.BaseTheme
import app.hdj.datepick.utils.location.LocationTracker
import coil.ImageLoader
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import com.google.accompanist.systemuicontroller.rememberSystemUiController


private val mainNavigationRoutesWithIcon = listOf(
    BottomNavigationProperty(Icons.Rounded.Home, "홈", AppNavigationGraph.Main.Home),
    BottomNavigationProperty(Icons.Rounded.Map, "지도", AppNavigationGraph.Main.Map),
    BottomNavigationProperty(Icons.Rounded.Book, "내 데이트", AppNavigationGraph.Main.MyDate),
    BottomNavigationProperty(
        Icons.Rounded.Person,
        "프로필",
        AppNavigationGraph.Main.Profile
    ),
)


@OptIn(
    ExperimentalAnimationApi::class,
    ExperimentalMaterialNavigationApi::class,
)
@Composable
fun DatePickApp(
    appViewModel: DatePickAppViewModel,
    imageLoader: ImageLoader,
    locationTracker: LocationTracker,
    supportFragmentManager: FragmentManager
) {
    DatePickComposableProviderScope(appViewModel, imageLoader, locationTracker) {

        val (state) = LocalDatePickAppViewModel.current.extract()

        val isSystemInDarkTheme = isSystemInDarkTheme()

        val isDarkTheme = when (state.appTheme) {
            AppSettings.AppTheme.Light -> false
            AppSettings.AppTheme.Dark -> true
            AppSettings.AppTheme.System -> isSystemInDarkTheme
            else -> isSystemInDarkTheme
        }

        val systemUiController = rememberSystemUiController()

        LaunchedEffect(isDarkTheme) {
            systemUiController.systemBarsDarkContentEnabled = !isDarkTheme
        }

        BaseTheme(isDarkTheme = isDarkTheme) {

            val navController = rememberAnimatedNavController()

            val bottomSheetNavigator = rememberBottomSheetNavigator()

            navController.navigatorProvider += bottomSheetNavigator

            val navBackStackEntry by navController.currentBackStackEntryAsState()

            val currentRoute = navBackStackEntry?.destination?.route

            CompositionLocalProvider(
                LocalAppNavController provides navController,
                LocalFragmentManager provides supportFragmentManager
            ) {

                ModalBottomSheetLayout(
                    bottomSheetNavigator = bottomSheetNavigator,
                    sheetBackgroundColor = Color.Unspecified
                ) {
                    BaseScaffold(
                        bottomBar = {
                            NavigationGraphBottomNavigation(
                                list = mainNavigationRoutesWithIcon,
                                navController = navController
                            )
                        }
                    ) {
                        DatepickScreenNavHost()
                    }
                }

            }

        }
    }

}
