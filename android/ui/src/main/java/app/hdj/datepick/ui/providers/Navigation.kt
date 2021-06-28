package app.hdj.datepick.ui.providers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavController

val LocalParentNavController = staticCompositionLocalOf<NavController> {
    error("Not Provided!")
}

@Composable
fun ProvideParentNavController(navController: NavController, content : @Composable () -> Unit) {
    CompositionLocalProvider(LocalParentNavController provides navController) {
        content()
    }
}