package app.hdj.datepick.ui.utils

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun NavController.currentScreenRoute(): String? {
    return currentBackStackEntry?.destination?.route
}