package app.hdj.datepick.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import app.hdj.datepick.ui.utils.NavigationGraph
import app.hdj.datepick.ui.utils.currentScreenRoute
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.navigationBarsHeight
import com.google.accompanist.insets.rememberInsetsPaddingValues

data class BottomNavigationProperty(
    val icon: ImageVector,
    val label: String,
    val navigation: NavigationGraph,
    val badgeEnabled: Boolean = false
)

@Composable
fun NavigationGraphBottomNavigation(
    modifier: Modifier = Modifier,
    navController: NavController,
    list: List<BottomNavigationProperty>
) {
    Column {
        Divider(color = MaterialTheme.colors.onBackground.copy(alpha = 0.03f))
        com.google.accompanist.insets.ui.BottomNavigation(
            modifier = Modifier.navigationBarsHeight(64.dp),
            backgroundColor = MaterialTheme.colors.background,
            elevation = 0.dp,
            contentPadding = rememberInsetsPaddingValues(LocalWindowInsets.current.navigationBars)
        ) {
            list.forEach { (icon, label, navigation, badgeEnabled) ->
                BottomNavigationItem(
                    navController.currentScreenRoute() == navigation.route,
                    icon = {
                        BadgedBox(badge = {
                            if (badgeEnabled) Badge()
                        }) {
                            Column(modifier = Modifier.padding(bottom = 8.dp)) {
                                Icon(icon, null, modifier = Modifier.size(20.dp))
                            }
                        }
                    },
                    label = { Text(label) },
                    selectedContentColor = MaterialTheme.colors.onBackground,
                    unselectedContentColor = MaterialTheme.colors.onBackground.copy(alpha = 0.15f),
                    onClick = {
                        navController.navigate(navigation.route) {
                            launchSingleTop = true
                            popUpTo(list.first().navigation.route)
                        }
                    }
                )
            }
        }
    }
}