package app.hdj.datepick.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import app.hdj.datepick.ui.utils.currentScreenRoute
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues

@Composable
fun NavigationGraphBottomNavigation(
    navController: NavController,
    list: Map<ImageVector, String>
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
        list.forEach { (icon, route) ->
            BottomNavigationItem(
                selected = route == navController.currentScreenRoute(),
                onClick = { navController.navigate(route) },
                icon = { Icon(icon, null) },
                unselectedContentColor = LocalContentColor.current.copy(alpha = 0.4f)
            )
        }
    }

}