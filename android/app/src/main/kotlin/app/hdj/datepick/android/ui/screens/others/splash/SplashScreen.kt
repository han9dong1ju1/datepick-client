package app.hdj.datepick.android.ui.screens.others.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import app.hdj.datepick.android.ui.DatePickAppViewModelDelegate.Effect.OpenMainScreen
import app.hdj.datepick.android.ui.DatePickAppViewModelDelegate.Effect.ShowNetworkErrorDialog
import app.hdj.datepick.android.ui.LocalDatePickAppViewModel
import app.hdj.datepick.android.ui.icons.DatePickIcon
import app.hdj.datepick.android.ui.icons.DatePickIcons
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import app.hdj.datepick.android.ui.screens.AppNavigationGraph
import app.hdj.datepick.android.ui.screens.appNavigationComposable
import app.hdj.datepick.android.ui.screens.navigateRoute
import app.hdj.datepick.ui.utils.collectInLaunchedEffect
import app.hdj.datepick.ui.utils.extract


fun NavGraphBuilder.splashScreenRoute() {
    appNavigationComposable(AppNavigationGraph.Splash) {

        val navController = LocalAppNavController.current
        val (state, effect, event) = LocalDatePickAppViewModel.current.extract()

        effect.collectInLaunchedEffect {
            when (it) {
                OpenMainScreen -> {
                    navController.navigateRoute(AppNavigationGraph.Main) {
                        launchSingleTop = true
                    }
                }
                ShowNetworkErrorDialog -> {
                    navController.navigateRoute(AppNavigationGraph.NetworkErrorDialog) {
                        launchSingleTop = true
                    }
                }
            }
        }

        Box(modifier = Modifier.fillMaxSize()) {
            Icon(
                modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.Center),
                imageVector = DatePickIcons.DatePickIcon,
                tint = Color.Unspecified,
                contentDescription = null
            )
        }
    }

}