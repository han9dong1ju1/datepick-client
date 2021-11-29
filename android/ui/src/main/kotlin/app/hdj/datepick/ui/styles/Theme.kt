package app.hdj.datepick.ui.styles

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
//    primary = Color(0xFF2E2E2E),
//    onPrimary = Color(0xFFE2E2E2),
//    primaryContainer = Color(0xFF222222),
//    onPrimaryContainer = Color(0xFFE2E2E2),
//    inversePrimary = Color(0xFF2E2E2E),
//
//    secondary = Color(0xFF48DFA2),
//    secondaryContainer = Color(0xFFFFFFFF),
//    onSecondaryContainer = Color(0xFF181818),
//
//    tertiary = Color(0xFF2E2E2E),
//    tertiaryContainer = Color(0xFFFFFFFF),
//    onTertiaryContainer = Color(0xFF181818),
//
//    background = Color(0xFF111111),
//
//    surface = Color(0xFF181818),
//    onSurface  = Color(0xFFFFFFFF),
//    surfaceVariant = Color(0xFF181818),
//    onSurfaceVariant  = Color(0xFFFFFFFF),
//
//    inverseSurface = Color(0xFF181818),
//    inverseOnSurface = Color(0xFFFFFFFF),
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFFC9C9C9),
    secondary = Color(0xFF000000),
    tertiary = Color(0xFF2E2E2E),
    secondaryContainer = Color(0xFFF7F7F7),
    onSecondaryContainer = Color(0xFF242424),
    background = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFF8F8F8),
    tertiaryContainer = Color(0xFFF1F1F1),
    surface = Color(0xFFFFFFFF),
)

@Composable
fun BaseTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val context = LocalContext.current

    val colorSchemes = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        if (isDarkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
    } else {
        if (isDarkTheme) DarkColorScheme else LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorSchemes,
        typography = Typographies,
        content = content
    )
}