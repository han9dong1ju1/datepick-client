package app.hdj.datepick.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import app.hdj.datepick.ui.utils.NavigationGraph
import app.hdj.datepick.ui.utils.currentScreenRoute
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues

data class BottomNavigationProperty(
    val icon : ImageVector,
    val label : String,
    val navigation : NavigationGraph
)

@Composable
fun NavigationGraphBottomNavigation(
    navController: NavController,
    list: List<BottomNavigationProperty>
) {

    com.google.accompanist.insets.ui.BottomNavigation(
        backgroundColor = MaterialTheme.colors.surface,
        modifier = Modifier.fillMaxWidth(),
        contentPadding = rememberInsetsPaddingValues(
            insets = LocalWindowInsets.current.navigationBars,
            applyStart = true,
            applyBottom = true,
            applyEnd = true,
        )
    ) {
        list.forEach { (icon, label, nav) ->
            BottomNavigationItem(
                selected = nav.route == navController.currentScreenRoute(),
                onClick = { navController.navigate(nav.route) },
                icon = { Icon(icon, null) },
                label = { Text(text = label) },
                unselectedContentColor = LocalContentColor.current.copy(alpha = 0.4f)
            )
        }
    }

}