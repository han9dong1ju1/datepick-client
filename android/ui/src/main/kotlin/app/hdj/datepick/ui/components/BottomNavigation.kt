package app.hdj.datepick.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import app.hdj.datepick.ui.utils.NavigationGraph
import app.hdj.datepick.ui.utils.currentScreenRoute
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues

data class BottomNavigationProperty(
    val icon: ImageVector,
    val label: String,
    val navigation: NavigationGraph,
    val badgeEnabled: Boolean = false
)

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NavigationGraphBottomNavigation(
    navController: NavController,
    list: List<BottomNavigationProperty>
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.surface)
    ) {
        Divider(color = MaterialTheme.colors.onSurface.copy(alpha = 0.03f))
        BottomNavigation(
            backgroundColor = Color.Transparent,
            contentColor = MaterialTheme.colors.onBackground,
            elevation = 0.dp,
            modifier = Modifier.padding(
                rememberInsetsPaddingValues(
                    insets = LocalWindowInsets.current.navigationBars,
                    applyStart = true,
                    applyBottom = true,
                    applyEnd = true,
                )
            )
        ) {
            list.forEach { (icon, label, nav, badgeEnabled) ->
                BottomNavigationItem(
                    selected = nav.route == navController.currentScreenRoute(),
                    onClick = { navController.navigate(nav.route) },
                    icon = {
                        if (badgeEnabled) {
                            BadgedBox(badge = {
                                Badge()
                            }) { Icon(icon, null) }
                        } else {
                            Icon(icon, null)
                        }
                    },
                    label = { Text(text = label) },
                    unselectedContentColor = LocalContentColor.current.copy(alpha = 0.2f)
                )
            }
        }
    }

}