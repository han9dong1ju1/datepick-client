package app.hdj.datepick.android.ui.providers

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavController

val LocalAppNavController = staticCompositionLocalOf<NavController> {
    error("Not Provided!!")
}