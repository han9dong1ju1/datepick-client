package app.hdj.datepick.android.ui.providers

import androidx.compose.runtime.compositionLocalOf
import com.google.accompanist.systemuicontroller.SystemUiController

val LocalSystemUiController  = compositionLocalOf<SystemUiController> {
    error("Failed")
}