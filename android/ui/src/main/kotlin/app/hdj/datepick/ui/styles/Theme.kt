package app.hdj.datepick.ui.styles

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun BaseTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val context = LocalContext.current

    MaterialTheme(
        typography = Typographies,
        colors =  if (isDarkTheme) darkColors() else lightColors()
    ) {
        content()
    }
}