package app.hdj.datepick.ui.styles

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.hdj.shared.client.domain.entity.settings.AppTheme

/* Typo */
val typographies = Typography(
    defaultFontFamily = FontFamily.Default,
    h1 = TextStyle(
        fontWeight = FontWeight.Black,
        fontSize = 24.sp
    ),
    h2 = TextStyle(
        fontWeight = FontWeight.Black,
        fontSize = 20.sp
    ),
    h3 = TextStyle(
        fontWeight = FontWeight.Black,
        fontSize = 18.sp
    ),
    h4 = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    ),
    h5 = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),
    h6 = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),
    subtitle1 = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    ),
    subtitle2 = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp
    ),
    body1 = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp
    ),
    body2 = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp
    ),
    button = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp
    ),
    caption = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 10.sp
    ),
    overline = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 10.sp
    )
)

/* Shapes */

val shapes = Shapes(
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(12.dp),
    large = RoundedCornerShape(20.dp)
)

/* Colors */

val darkColors = darkColors(
    primary = Color(0xff5cadff),
    secondary = Color(0xff5cadff),
    primaryVariant = Color(0xff5cadff),
    secondaryVariant = Color(0xff5cadff),
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
    primary = Color(0xff5cadff),
    secondary = Color(0xff5cadff),
    primaryVariant = Color(0xff5cadff),
    secondaryVariant = Color(0xff5cadff),
    background = Color.White,
    surface = Color.White,
    error = Color(0xFFB00020),
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    onError = Color.White
)

@Composable
fun DatePickTheme(
    appTheme: AppTheme = AppTheme.SYSTEM,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        typography = typographies,
        shapes = shapes,
        colors = when (appTheme) {
            AppTheme.LIGHT -> lightColors
            AppTheme.DARK -> darkColors
            AppTheme.SYSTEM -> if (isSystemInDarkTheme()) darkColors else lightColors
        },
        content = content
    )
}