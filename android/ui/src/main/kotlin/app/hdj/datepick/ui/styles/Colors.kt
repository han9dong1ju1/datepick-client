package app.hdj.datepick.ui.styles

import android.annotation.SuppressLint
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver

val Colors.onSurface50
    @Composable get() =
        onSurface.copy(0.5f).compositeOver(surface)

val Colors.onSurface30
    @Composable get() =
        onSurface.copy(0.3f).compositeOver(surface)

val Colors.onSurface10
    @Composable get() =
        onSurface.copy(0.1f).compositeOver(surface)

/* Colors */
val darkColors = darkColors(
    primary = Color(0xFF23B396),
    secondary = Color(0xFFef6d9a),
    primaryVariant = Color(0xFF23B396),
    secondaryVariant = Color(0xFFef6d9a),
    background = Color(0xFF131313),
    surface = Color(0xFF131313),
    error = Color(0xFFCF6679),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
    onError = Color.Black,
)

@SuppressLint("ConflictingOnColor")
val lightColors = lightColors(
    primary = Color(0xFF23B396),
    secondary = Color(0xFFef6d9a),
    primaryVariant = Color(0xFF23B396),
    secondaryVariant = Color(0xFFef6d9a),
    background = Color(0xFFf8f8f8),
    surface = Color.White,
    error = Color(0xFFB00020),
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    onError = Color.White
)
