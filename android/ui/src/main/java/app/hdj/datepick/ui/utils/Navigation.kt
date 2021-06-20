package app.hdj.datepick.ui.utils

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun NavController.currentScreenRoute(): String {
    val currentRoute = currentBackStackEntry?.destination?.route
    return currentRoute!!
}