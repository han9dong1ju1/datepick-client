package app.hdj.datepick.ui.components

import androidx.compose.material.Badge
import androidx.compose.material.BadgedBox
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import app.hdj.datepick.ui.utils.NavigationGraph
import app.hdj.datepick.ui.utils.currentScreenRoute

data class BottomNavigationProperty(
    val icon: ImageVector,
    val label: String,
    val navigation: NavigationGraph,
    val badgeEnabled: Boolean = false
)

@Composable
fun NavigationGraphBottomNavigation(
    modifier : Modifier = Modifier,
    navController: NavController,
    list: List<BottomNavigationProperty>
) {
    com.google.accompanist.insets.ui.BottomNavigation(
        modifier = modifier
    ) {
        list.forEach { (icon, label, nav, badgeEnabled) ->
            BottomNavigationItem(
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
                }

            )
        }
    }
}