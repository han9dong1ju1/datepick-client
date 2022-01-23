package app.hdj.datepick.android.ui.providers

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavController
import androidx.navigation.NavHostController

val LocalAppNavController = staticCompositionLocalOf<NavHostController> {
    error("Not Provided!!")
}