package app.hdj.datepick.android.ui.screens.main

import androidx.compose.animation.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import app.hdj.datepick.android.ui.screens.AppNavigationGraph
import app.hdj.datepick.android.ui.screens.navigateRoute

@Composable
fun MainFloatingActionButton(currentRoute: String?) {

    if (currentRoute == null) return

    val navController = LocalAppNavController.current

    AnimatedVisibility(
        visible = currentRoute == AppNavigationGraph.Main.Home.route,
        enter = fadeIn() + scaleIn(),
        exit = scaleOut() + fadeOut(),
    ) {
        FloatingActionButton(
            onClick = { navController.navigate(AppNavigationGraph.CreateCourse.route) },
            shape = CircleShape
        ) {
            Icon(imageVector = Icons.Rounded.Add, null)
        }
    }


}