package app.hdj.datepick.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

data class BottomNavigationProperty(
    val icon: ImageVector,
    val label: String,
    val navigation: String,
    val badgeEnabled: Boolean = false
)

@Composable
fun NavigationGraphBottomNavigation(
    modifier: Modifier = Modifier,
    navController: NavController,
    currentRoute: String,
    list: List<BottomNavigationProperty>
) {

    Surface(
        modifier = modifier,
        color = MaterialTheme.colors.background
    ) {
        Column(modifier = Modifier.navigationBarsPadding()) {
            Divider(color = MaterialTheme.colors.onBackground.copy(alpha = 0.03f))
            BottomNavigation(
                modifier = Modifier.height(64.dp),
                backgroundColor = Color.Transparent,
                elevation = 0.dp
            ) {
                list.forEach { (icon, label, navigation, badgeEnabled) ->
                    BottomNavigationItem(
                        currentRoute == navigation,
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
                            navController.navigate(navigation) {
                                launchSingleTop = true
                                popUpTo(list.first().navigation)
                            }
                        }
                    )
                }
            }
        }
    }

}