package app.hdj.datepick.ui.styles

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import app.hdj.shared.client.domain.entity.AppTheme

/* Typo */
val typographies = Typography(

)

/* Shapes */

val shapes = Shapes(
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(20.dp)
)

/* Colors */

val darkColors = darkColors(

)

val lightColors = lightColors(

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