package app.hdj.datepick.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
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
    modifier: Modifier = Modifier,
    navController: NavController,
    contentPaddingValues: PaddingValues,
    list: List<BottomNavigationProperty>
) {
    com.google.accompanist.insets.ui.BottomNavigation(
        modifier = modifier,
        contentPadding = contentPaddingValues,
        backgroundColor = MaterialTheme.colors.background,
        elevation = 0.dp,
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
                },
                selectedContentColor = MaterialTheme.colors.onBackground,
                unselectedContentColor = MaterialTheme.colors.onBackground.copy(0.1f),

                )
        }
    }
}