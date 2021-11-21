package app.hdj.datepick.ui.styles

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

private val font = FontFamily.Default

internal object TypeScale {

    val BodyLargeFont = font
    val BodyLargeLineHeight = 24.0.sp
    val BodyLargeSize = 16.sp
    val BodyLargeTracking = 0.5.sp
    val BodyLargeWeight = FontWeight.Medium

    val BodyMediumFont = font
    val BodyMediumLineHeight = 20.0.sp
    val BodyMediumSize = 14.sp
    val BodyMediumTracking = 0.2.sp
    val BodyMediumWeight = FontWeight.Medium

    val BodySmallFont = font
    val BodySmallLineHeight = 16.0.sp
    val BodySmallSize = 12.sp
    val BodySmallTracking = 0.4.sp
    val BodySmallWeight = FontWeight.Medium

    val DisplayLargeFont = font
    val DisplayLargeLineHeight = 64.0.sp
    val DisplayLargeSize = 57.sp
    val DisplayLargeTracking = (-0.2).sp
    val DisplayLargeWeight = FontWeight.Medium

    val DisplayMediumFont = font
    val DisplayMediumLineHeight = 52.0.sp
    val DisplayMediumSize = 45.sp
    val DisplayMediumTracking = 0.0.sp
    val DisplayMediumWeight = FontWeight.Medium

    val DisplaySmallFont = font
    val DisplaySmallLineHeight = 44.0.sp
    val DisplaySmallSize = 36.sp
    val DisplaySmallTracking = 0.0.sp
    val DisplaySmallWeight = FontWeight.Medium

    val HeadlineLargeFont = font
    val HeadlineLargeLineHeight = 40.0.sp
    val HeadlineLargeSize = 32.sp
    val HeadlineLargeTracking = 0.0.sp
    val HeadlineLargeWeight = FontWeight.Bold

    val HeadlineMediumFont = font
    val HeadlineMediumLineHeight = 36.0.sp
    val HeadlineMediumSize = 28.sp
    val HeadlineMediumTracking = 0.0.sp
    val HeadlineMediumWeight = FontWeight.Bold

    val HeadlineSmallFont = font
    val HeadlineSmallLineHeight = 32.0.sp
    val HeadlineSmallSize = 24.sp
    val HeadlineSmallTracking = 0.0.sp
    val HeadlineSmallWeight = FontWeight.Bold

    val LabelLargeFont = font
    val LabelLargeLineHeight = 20.0.sp
    val LabelLargeSize = 14.sp
    val LabelLargeTracking = 0.1.sp
    val LabelLargeWeight = FontWeight.Medium

    val LabelMediumFont = font
    val LabelMediumLineHeight = 16.0.sp
    val LabelMediumSize = 12.sp
    val LabelMediumTracking = 0.5.sp
    val LabelMediumWeight = FontWeight.Medium

    val LabelSmallFont = font
    val LabelSmallLineHeight = 16.0.sp
    val LabelSmallSize = 11.sp
    val LabelSmallTracking = 0.5.sp
    val LabelSmallWeight = FontWeight.Medium

    val TitleLargeFont = font
    val TitleLargeLineHeight = 28.0.sp
    val TitleLargeSize = 22.sp
    val TitleLargeTracking = 0.0.sp
    val TitleLargeWeight = FontWeight.Bold

    val TitleMediumFont = font
    val TitleMediumLineHeight = 24.0.sp
    val TitleMediumSize = 16.sp
    val TitleMediumTracking = 0.2.sp
    val TitleMediumWeight = FontWeight.Bold

    val TitleSmallFont = font
    val TitleSmallLineHeight = 20.0.sp
    val TitleSmallSize = 14.sp
    val TitleSmallTracking = 0.1.sp
    val TitleSmallWeight = FontWeight.Bold
}

private object AppTypography {
    val BodyLarge = TextStyle(
        fontFamily = TypeScale.BodyLargeFont,
        fontWeight = TypeScale.BodyLargeWeight,
        fontSize = TypeScale.BodyLargeSize,
        lineHeight = TypeScale.BodyLargeLineHeight,
        letterSpacing = TypeScale.BodyLargeTracking,
    )
    val BodyMedium = TextStyle(
        fontFamily = TypeScale.BodyMediumFont,
        fontWeight = TypeScale.BodyMediumWeight,
        fontSize = TypeScale.BodyMediumSize,
        lineHeight = TypeScale.BodyMediumLineHeight,
        letterSpacing = TypeScale.BodyMediumTracking,
    )
    val BodySmall = TextStyle(
        fontFamily = TypeScale.BodySmallFont,
        fontWeight = TypeScale.BodySmallWeight,
        fontSize = TypeScale.BodySmallSize,
        lineHeight = TypeScale.BodySmallLineHeight,
        letterSpacing = TypeScale.BodySmallTracking,
    )
    val DisplayLarge = TextStyle(
        fontFamily = TypeScale.DisplayLargeFont,
        fontWeight = TypeScale.DisplayLargeWeight,
        fontSize = TypeScale.DisplayLargeSize,
        lineHeight = TypeScale.DisplayLargeLineHeight,
        letterSpacing = TypeScale.DisplayLargeTracking,
    )
    val DisplayMedium = TextStyle(
        fontFamily = TypeScale.DisplayMediumFont,
        fontWeight = TypeScale.DisplayMediumWeight,
        fontSize = TypeScale.DisplayMediumSize,
        lineHeight = TypeScale.DisplayMediumLineHeight,
        letterSpacing = TypeScale.DisplayMediumTracking,
    )
    val DisplaySmall = TextStyle(
        fontFamily = TypeScale.DisplaySmallFont,
        fontWeight = TypeScale.DisplaySmallWeight,
        fontSize = TypeScale.DisplaySmallSize,
        lineHeight = TypeScale.DisplaySmallLineHeight,
        letterSpacing = TypeScale.DisplaySmallTracking,
    )
    val HeadlineLarge = TextStyle(
        fontFamily = TypeScale.HeadlineLargeFont,
        fontWeight = TypeScale.HeadlineLargeWeight,
        fontSize = TypeScale.HeadlineLargeSize,
        lineHeight = TypeScale.HeadlineLargeLineHeight,
        letterSpacing = TypeScale.HeadlineLargeTracking,
    )
    val HeadlineMedium = TextStyle(
        fontFamily = TypeScale.HeadlineMediumFont,
        fontWeight = TypeScale.HeadlineMediumWeight,
        fontSize = TypeScale.HeadlineMediumSize,
        lineHeight = TypeScale.HeadlineMediumLineHeight,
        letterSpacing = TypeScale.HeadlineMediumTracking,
    )
    val HeadlineSmall = TextStyle(
        fontFamily = TypeScale.HeadlineSmallFont,
        fontWeight = TypeScale.HeadlineSmallWeight,
        fontSize = TypeScale.HeadlineSmallSize,
        lineHeight = TypeScale.HeadlineSmallLineHeight,
        letterSpacing = TypeScale.HeadlineSmallTracking,
    )
    val LabelLarge = TextStyle(
        fontFamily = TypeScale.LabelLargeFont,
        fontWeight = TypeScale.LabelLargeWeight,
        fontSize = TypeScale.LabelLargeSize,
        lineHeight = TypeScale.LabelLargeLineHeight,
        letterSpacing = TypeScale.LabelLargeTracking,
    )
    val LabelMedium = TextStyle(
        fontFamily = TypeScale.LabelMediumFont,
        fontWeight = TypeScale.LabelMediumWeight,
        fontSize = TypeScale.LabelMediumSize,
        lineHeight = TypeScale.LabelMediumLineHeight,
        letterSpacing = TypeScale.LabelMediumTracking,
    )
    val LabelSmall = TextStyle(
        fontFamily = TypeScale.LabelSmallFont,
        fontWeight = TypeScale.LabelSmallWeight,
        fontSize = TypeScale.LabelSmallSize,
        lineHeight = TypeScale.LabelSmallLineHeight,
        letterSpacing = TypeScale.LabelSmallTracking,
    )
    val TitleLarge = TextStyle(
        fontFamily = TypeScale.TitleLargeFont,
        fontWeight = TypeScale.TitleLargeWeight,
        fontSize = TypeScale.TitleLargeSize,
        lineHeight = TypeScale.TitleLargeLineHeight,
        letterSpacing = TypeScale.TitleLargeTracking,
    )
    val TitleMedium = TextStyle(
        fontFamily = TypeScale.TitleMediumFont,
        fontWeight = TypeScale.TitleMediumWeight,
        fontSize = TypeScale.TitleMediumSize,
        lineHeight = TypeScale.TitleMediumLineHeight,
        letterSpacing = TypeScale.TitleMediumTracking,
    )
    val TitleSmall = TextStyle(
        fontFamily = TypeScale.TitleSmallFont,
        fontWeight = TypeScale.TitleSmallWeight,
        fontSize = TypeScale.TitleSmallSize,
        lineHeight = TypeScale.TitleSmallLineHeight,
        letterSpacing = TypeScale.TitleSmallTracking,
    )
}

val Typographies = Typography(
    displayLarge = AppTypography.DisplayLarge,
    displayMedium = AppTypography.DisplayMedium,
    displaySmall = AppTypography.DisplaySmall,
    headlineLarge = AppTypography.HeadlineLarge,
    headlineMedium = AppTypography.HeadlineMedium,
    headlineSmall = AppTypography.HeadlineSmall,
    titleLarge = AppTypography.TitleLarge,
    titleMedium = AppTypography.TitleMedium,
    titleSmall = AppTypography.TitleSmall,
    bodyLarge = AppTypography.BodyLarge,
    bodyMedium = AppTypography.BodyMedium,
    bodySmall = AppTypography.BodySmall,
    labelLarge = AppTypography.LabelLarge,
    labelMedium = AppTypography.LabelMedium,
    labelSmall = AppTypography.LabelSmall,
)