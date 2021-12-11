package app.hdj.datepick.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import app.hdj.datepick.ui.utils.NavigationGraph
import app.hdj.datepick.ui.utils.currentScreenRoute
import com.google.accompanist.insets.navigationBarsHeight

data class BottomNavigationProperty(
    val icon: ImageVector,
    val label: String,
    val navigation: NavigationGraph,
    val badgeEnabled: Boolean = false
)

@Composable
fun NavigationGraphBottomNavigation(
    navController: NavController,
    list: List<BottomNavigationProperty>
) {

    HighSurface(
        modifier = Modifier
            .navigationBarsHeight(56.dp)
            .fillMaxWidth()
    ) {
        Column {
            NavigationBar(
                modifier = Modifier.height(56.dp),
                containerColor = Color.Unspecified
            ) {
                list.forEach { (icon, label, nav, badgeEnabled) ->
                    NavigationBarItem(
                        selected = nav.route == navController.currentScreenRoute(),
                        onClick = {
                            navController.navigate(nav.route) {
                                launchSingleTop = true
                                popUpTo(list.first().navigation.route)
                            }
                        },
                        icon = {
                            if (badgeEnabled) {
                                BadgedBox(badge = {
                                    Badge()
                                }) { Icon(icon, null) }
                            } else {
                                Icon(icon, null)
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(
                                0.2f
                            )
                        )
                    )
                }
            }

            Box(Modifier.navigationBarsHeight())
        }

    }

}