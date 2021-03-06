package app.hdj.datepick.ui.styles

import androidx.compose.material.Colors
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

val darkColors = darkColors(
    primary = Color(0xFFe24b68),
    primaryVariant = Color(0xFFD12647),
    secondary = Color(0xFFe24b68),
    secondaryVariant = Color(0xFFD12647),
    background = Color(0xFF111214),
    surface = Color(0xFF141519),
    error = Color(0xFFCF6679),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color(0xFFEDF0F8),
    onError = Color.White
)

val lightColors = lightColors(
    primary = Color(0xFFe24b68),
    primaryVariant = Color(0xFFD12647),
    secondary = Color(0xFFe24b68),
    secondaryVariant = Color(0xFFD12647),
    background = Color.White,
    surface = Color(0xFFF9F9FC),
    error = Color(0xFFB00020),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.Black,
    onSurface = Color(0xFF181C26),
    onError = Color.White
)

val Colors.tertiary get() = if (isLight) Color(0xFFe24b68) else Color(0xFF361621)