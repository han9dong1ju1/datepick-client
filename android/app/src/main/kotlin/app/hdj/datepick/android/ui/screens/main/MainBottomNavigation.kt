package app.hdj.datepick.android.ui.screens.main

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.SignalWifiConnectedNoInternet4
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import app.hdj.datepick.android.ui.screens.AppNavigationGraph
import app.hdj.datepick.android.utils.ConnectionState
import app.hdj.datepick.android.utils.connectivityState
import app.hdj.datepick.ui.components.BottomNavigationProperty
import app.hdj.datepick.ui.components.NavigationGraphBottomNavigation
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.navigationBarsHeight
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.rememberInsetsPaddingValues

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainBottomNavigation(
    mainNavigationRoutesWithIcon: List<BottomNavigationProperty>,
    currentRoute: String?
) {

    val navController = LocalAppNavController.current

    val isRouteAllowedForBottomNavigation = remember(currentRoute) {
        (mainNavigationRoutesWithIcon.map { it.navigation } + listOf(
            AppNavigationGraph.AppUpdateDialog,
            AppNavigationGraph.ExitDialog,
            AppNavigationGraph.LoginDialog,
        )).map { it.route }.contains(currentRoute)
    }

    val connectivityState by connectivityState()

    AnimatedVisibility(
        visible = isRouteAllowedForBottomNavigation,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it })
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .animateContentSize()
        ) {

            Divider(color = MaterialTheme.colors.onBackground.copy(0.02f))

            NavigationGraphBottomNavigation(
                navController = navController,
                contentPaddingValues = if (connectivityState == ConnectionState.Available)
                    rememberInsetsPaddingValues(LocalWindowInsets.current.navigationBars)
                else PaddingValues(),
                list = mainNavigationRoutesWithIcon
            )

            AnimatedVisibility(connectivityState == ConnectionState.Unavailable) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .navigationBarsHeight(36.dp),
                    color = MaterialTheme.colors.error
                ) {
                    Row(
                        modifier = Modifier.padding(10.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.Top
                    ) {
                        Icon(
                            modifier = Modifier.size(14.dp),
                            imageVector = Icons.Rounded.SignalWifiConnectedNoInternet4,
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "네트워크 상태를 확인해주세요.",
                            style = MaterialTheme.typography.body2
                        )
                    }
                }
            }
        }
    }
}