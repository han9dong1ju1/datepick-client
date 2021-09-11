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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/* Typo */
val typographies = Typography(
    defaultFontFamily = FontFamily.Default,
    h1 = TextStyle(
        fontWeight = FontWeight.Black,
        fontSize = 24.sp,
        lineHeight = 36.sp,
        letterSpacing = 1.sp,
    ),
    h2 = TextStyle(
        fontWeight = FontWeight.Black,
        fontSize = 20.sp,
        lineHeight = 30.sp,
        letterSpacing = 1.sp,
    ),
    h3 = TextStyle(
        fontWeight = FontWeight.Black,
        fontSize = 18.sp,
        lineHeight = 24.sp,
        letterSpacing = 1.sp,
    ),
    h4 = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        lineHeight = 24.sp,
        letterSpacing = 1.sp,
    ),
    h5 = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 20.sp,
        letterSpacing = 1.sp,
    ),
    h6 = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.7.sp,
    ),
    subtitle1 = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.7.sp,
    ),
    subtitle2 = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.7.sp,
    ),
    body1 = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.7.sp,
    ),
    body2 = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.7.sp,
    ),
    button = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.7.sp,
    ),
    caption = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 10.sp,
        lineHeight = 14.sp,
        letterSpacing = 0.7.sp,
    ),
    overline = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 10.sp,
        lineHeight = 14.sp,
        letterSpacing = 0.7.sp,
    )
)

/* Shapes */

val shapes = Shapes(
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(12.dp),
    large = RoundedCornerShape(20.dp)
)

@Composable
fun DatePickTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        typography = typographies,
        shapes = shapes,
        colors = lightColors,
        content = content
    )
}