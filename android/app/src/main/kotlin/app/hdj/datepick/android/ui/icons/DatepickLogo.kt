package app.hdj.datepick.android.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val DatePickIcons.Logo: ImageVector
    get() {
        if (_datepickLogo != null) {
            return _datepickLogo!!
        }
        _datepickLogo = Builder(name = "DatepickLogo", defaultWidth = 100.0.dp, defaultHeight =
                20.0.dp, viewportWidth = 100.0f, viewportHeight = 20.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.0f, strokeAlpha
                    = 0.0f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(0.0f, 0.0f)
                horizontalLineToRelative(100.0f)
                verticalLineToRelative(20.0f)
                horizontalLineToRelative(-100.0f)
                close()
            }
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(14.739f, 9.815f)
                arcToRelative(7.035f, 7.035f, 0.0f, false, false, -1.758f, -4.748f)
                arcToRelative(6.248f, 6.248f, 0.0f, false, false, -4.7f, -2.056f)
                lineTo(3.745f, 3.011f)
                arcTo(1.745f, 1.745f, 0.0f, false, false, 2.0f, 4.752f)
                lineTo(2.0f, 14.87f)
                arcToRelative(1.754f, 1.754f, 0.0f, false, false, 0.03f, 0.32f)
                lineToRelative(0.0f, 0.009f)
                curveToRelative(0.009f, 0.047f, 0.02f, 0.093f, 0.032f, 0.138f)
                arcTo(1.745f, 1.745f, 0.0f, false, false, 3.745f, 16.615f)
                lineTo(8.284f, 16.615f)
                arcTo(6.65f, 6.65f, 0.0f, false, false, 9.372f, 16.526f)
                lineToRelative(0.021f, 0.0f)
                arcToRelative(6.446f, 6.446f, 0.0f, false, false, 0.632f, -0.14f)
                arcToRelative(6.164f, 6.164f, 0.0f, false, false, 1.764f, -0.8f)
                arcToRelative(6.237f, 6.237f, 0.0f, false, false, 1.192f, -1.024f)
                arcToRelative(6.476f, 6.476f, 0.0f, false, false, 0.655f, -0.859f)
                curveToRelative(0.02f, -0.031f, 0.039f, -0.063f, 0.058f, -0.095f)
                arcTo(6.788f, 6.788f, 0.0f, false, false, 14.2f, 12.615f)
                arcToRelative(7.259f, 7.259f, 0.0f, false, false, 0.493f, -1.944f)
                arcToRelative(7.7f, 7.7f, 0.0f, false, false, 0.048f, -0.858f)
                close()
                moveTo(5.489f, 6.5f)
                lineTo(8.284f, 6.5f)
                arcToRelative(2.763f, 2.763f, 0.0f, false, true, 2.109f, 0.908f)
                arcToRelative(3.259f, 3.259f, 0.0f, false, true, 0.706f, 1.3f)
                curveToRelative(0.032f, 0.114f, 0.059f, 0.23f, 0.081f, 0.349f)
                curveToRelative(0.013f, 0.068f, 0.024f, 0.137f, 0.033f, 0.206f)
                arcToRelative(4.171f, 4.171f, 0.0f, false, true, 0.036f, 0.55f)
                arcToRelative(3.994f, 3.994f, 0.0f, false, true, -0.185f, 1.219f)
                curveToRelative(-0.021f, 0.067f, -0.045f, 0.133f, -0.07f, 0.2f)
                arcToRelative(3.246f, 3.246f, 0.0f, false, true, -0.4f, 0.741f)
                curveToRelative(-0.019f, 0.027f, -0.039f, 0.052f, -0.059f, 0.078f)
                lineToRelative(-0.019f, 0.024f)
                curveToRelative(-0.025f, 0.031f, -0.05f, 0.062f, -0.076f, 0.092f)
                lineTo(10.393f, 12.215f)
                arcToRelative(2.772f, 2.772f, 0.0f, false, true, -0.465f, 0.411f)
                curveToRelative(-0.047f, 0.033f, -0.1f, 0.064f, -0.144f, 0.094f)
                lineToRelative(-0.011f, 0.007f)
                curveToRelative(-0.049f, 0.029f, -0.1f, 0.057f, -0.15f, 0.084f)
                arcToRelative(2.643f, 2.643f, 0.0f, false, true, -0.353f, 0.151f)
                quadToRelative(-0.078f, 0.027f, -0.158f, 0.05f)
                lineToRelative(-0.006f, 0.0f)
                curveToRelative(-0.054f, 0.015f, -0.108f, 0.028f, -0.163f, 0.04f)
                arcToRelative(3.1f, 3.1f, 0.0f, false, true, -0.659f, 0.069f)
                lineTo(5.489f, 13.121f)
                close()
                moveTo(29.208f, 14.872f)
                arcToRelative(1.731f, 1.731f, 0.0f, false, false, -0.027f, -0.315f)
                lineToRelative(0.022f, 0.013f)
                lineTo(29.2f, 14.527f)
                arcToRelative(1.718f, 1.718f, 0.0f, false, false, -0.049f, -0.194f)
                lineToRelative(-0.013f, -0.041f)
                lineTo(29.12f, 14.315f)
                arcToRelative(1.772f, 1.772f, 0.0f, false, false, -0.1f, -0.241f)
                lineToRelative(-0.375f, -0.738f)
                lineToRelative(0.007f, -0.017f)
                lineToRelative(0.0f, -0.008f)
                lineToRelative(-0.1f, -0.189f)
                lineToRelative(-0.008f, 0.0f)
                lineToRelative(-0.018f, 0.0f)
                lineToRelative(-0.138f, -0.271f)
                lineToRelative(0.0f, 0.0f)
                lineToRelative(0.012f, -0.01f)
                lineToRelative(-0.137f, -0.27f)
                lineToRelative(-0.015f, 0.0f)
                horizontalLineToRelative(0.0f)
                lineToRelative(-0.123f, -0.243f)
                horizontalLineToRelative(0.0f)
                lineToRelative(0.016f, -0.009f)
                lineTo(28.0f, 12.032f)
                lineToRelative(-0.017f, 0.007f)
                horizontalLineToRelative(0.0f)
                lineToRelative(-0.123f, -0.243f)
                lineToRelative(0.0f, 0.0f)
                lineToRelative(0.012f, -0.01f)
                lineToRelative(-0.137f, -0.27f)
                lineToRelative(-0.015f, 0.0f)
                horizontalLineToRelative(0.0f)
                lineTo(23.89f, 3.974f)
                lineToRelative(-0.01f, -0.02f)
                arcToRelative(1.745f, 1.745f, 0.0f, false, false, -1.555f, -0.955f)
                arcToRelative(1.745f, 1.745f, 0.0f, false, false, -1.555f, 0.955f)
                lineToRelative(-0.612f, 1.2f)
                lineTo(15.933f, 13.475f)
                lineToRelative(-0.3f, 0.587f)
                lineToRelative(-0.008f, -0.027f)
                lineToRelative(-0.022f, 0.045f)
                arcToRelative(1.791f, 1.791f, 0.0f, false, false, -0.072f, 0.166f)
                lineToRelative(-0.017f, 0.046f)
                lineToRelative(0.026f, -0.013f)
                arcToRelative(1.742f, 1.742f, 0.0f, false, false, -0.077f, 0.292f)
                lineToRelative(-0.017f, -0.013f)
                lineToRelative(-0.005f, 0.033f)
                arcToRelative(1.756f, 1.756f, 0.0f, false, false, -0.021f, 0.237f)
                verticalLineToRelative(0.033f)
                lineToRelative(0.02f, -0.011f)
                arcToRelative(1.745f, 1.745f, 0.0f, false, false, 0.793f, 1.477f)
                lineToRelative(-0.019f, 0.011f)
                lineToRelative(0.027f, 0.017f)
                curveToRelative(0.048f, 0.031f, 0.1f, 0.058f, 0.144f, 0.082f)
                lineToRelative(0.068f, 0.032f)
                lineToRelative(0.029f, 0.013f)
                lineTo(16.482f, 16.46f)
                arcToRelative(1.735f, 1.735f, 0.0f, false, false, 0.747f, 0.148f)
                arcTo(0.159f, 0.159f, 0.0f, false, false, 17.274f, 16.615f)
                arcToRelative(0.16f, 0.16f, 0.0f, false, false, 0.064f, -0.014f)
                arcToRelative(1.744f, 1.744f, 0.0f, false, false, 1.4f, -0.948f)
                lineToRelative(0.168f, -0.331f)
                lineToRelative(0.0f, 0.05f)
                lineToRelative(0.136f, -0.268f)
                lineToRelative(-0.038f, 0.032f)
                lineToRelative(0.14f, -0.275f)
                lineToRelative(0.017f, 0.009f)
                lineToRelative(0.011f, -0.021f)
                horizontalLineToRelative(0.138f)
                lineToRelative(0.005f, 0.0f)
                arcToRelative(0.14f, 0.14f, 0.0f, false, false, 0.015f, -0.015f)
                horizontalLineToRelative(0.785f)
                reflectiveCurveToRelative(0.0f, 0.007f, 0.0f, 0.01f)
                lineToRelative(0.006f, 0.009f)
                horizontalLineToRelative(0.259f)
                lineToRelative(0.006f, -0.009f)
                reflectiveCurveToRelative(0.0f, -0.007f, 0.0f, -0.01f)
                horizontalLineToRelative(0.628f)
                arcToRelative(0.164f, 0.164f, 0.0f, false, false, 0.058f, 0.02f)
                horizontalLineToRelative(0.04f)
                arcToRelative(0.156f, 0.156f, 0.0f, false, false, 0.057f, -0.019f)
                lineTo(21.6f, 14.835f)
                arcToRelative(0.163f, 0.163f, 0.0f, false, false, 0.058f, 0.02f)
                lineTo(21.7f, 14.855f)
                arcToRelative(0.157f, 0.157f, 0.0f, false, false, 0.057f, -0.019f)
                lineTo(25.49f, 14.836f)
                lineToRelative(0.008f, 0.017f)
                lineToRelative(-0.018f, 0.007f)
                lineToRelative(0.145f, 0.286f)
                lineToRelative(0.016f, -0.011f)
                lineToRelative(0.267f, 0.525f)
                arcToRelative(1.738f, 1.738f, 0.0f, false, false, 0.431f, 0.543f)
                arcToRelative(0.171f, 0.171f, 0.0f, false, false, 0.009f, 0.031f)
                lineToRelative(0.0f, 0.0f)
                lineToRelative(0.0f, 0.0f)
                curveToRelative(0.035f, 0.028f, 0.074f, 0.057f, 0.118f, 0.086f)
                lineToRelative(0.013f, 0.0f)
                lineTo(26.5f, 16.32f)
                lineToRelative(0.016f, 0.0f)
                arcToRelative(1.752f, 1.752f, 0.0f, false, false, 0.953f, 0.284f)
                arcToRelative(1.738f, 1.738f, 0.0f, false, false, 0.789f, -0.19f)
                arcToRelative(1.775f, 1.775f, 0.0f, false, false, 0.174f, -0.1f)
                arcTo(0.161f, 0.161f, 0.0f, false, false, 28.5f, 16.27f)
                arcToRelative(1.745f, 1.745f, 0.0f, false, false, 0.7f, -1.169f)
                lineToRelative(0.017f, 0.025f)
                lineToRelative(0.006f, -0.052f)
                arcToRelative(1.716f, 1.716f, 0.0f, false, false, 0.012f, -0.173f)
                verticalLineToRelative(-0.053f)
                close()
                moveTo(23.431f, 10.772f)
                lineTo(23.431f, 10.772f)
                lineTo(23.418f, 10.782f)
                lineTo(23.557f, 11.056f)
                lineTo(23.572f, 11.056f)
                horizontalLineToRelative(0.0f)
                lineToRelative(0.145f, 0.286f)
                horizontalLineToRelative(-0.181f)
                verticalLineToRelative(-0.019f)
                horizontalLineToRelative(-0.3f)
                lineToRelative(-0.017f, 0.0f)
                lineToRelative(0.0f, 0.015f)
                horizontalLineToRelative(-0.976f)
                lineToRelative(-0.011f, -0.014f)
                lineToRelative(-0.006f, -0.006f)
                lineTo(22.01f, 11.318f)
                lineToRelative(-0.006f, 0.006f)
                lineToRelative(-0.011f, 0.014f)
                horizontalLineToRelative(-0.3f)
                verticalLineToRelative(-0.005f)
                lineToRelative(0.0f, -0.014f)
                horizontalLineToRelative(-0.3f)
                lineToRelative(0.0f, 0.014f)
                verticalLineToRelative(0.005f)
                lineTo(21.1f, 11.338f)
                reflectiveCurveToRelative(0.0f, -0.005f, 0.0f, -0.008f)
                lineToRelative(-0.005f, -0.011f)
                horizontalLineToRelative(-0.126f)
                lineTo(21.0f, 11.245f)
                lineToRelative(-0.02f, 0.0f)
                lineToRelative(0.086f, -0.17f)
                lineToRelative(0.006f, 0.0f)
                lineToRelative(0.014f, 0.0f)
                lineToRelative(0.113f, -0.222f)
                lineToRelative(-0.024f, 0.0f)
                lineToRelative(1.149f, -2.261f)
                lineToRelative(1.062f, 2.091f)
                close()
                moveTo(39.5f, 5.952f)
                lineToRelative(0.013f, -0.014f)
                lineToRelative(0.039f, -0.044f)
                lineToRelative(0.015f, -0.018f)
                lineToRelative(0.035f, -0.043f)
                lineToRelative(0.017f, -0.021f)
                lineToRelative(0.031f, -0.042f)
                lineToRelative(0.017f, -0.025f)
                lineToRelative(0.028f, -0.042f)
                lineToRelative(0.018f, -0.028f)
                lineToRelative(0.025f, -0.041f)
                lineToRelative(0.017f, -0.03f)
                lineToRelative(0.022f, -0.041f)
                lineToRelative(0.017f, -0.033f)
                lineToRelative(0.02f, -0.041f)
                lineToRelative(0.016f, -0.035f)
                lineToRelative(0.017f, -0.04f)
                lineToRelative(0.015f, -0.037f)
                lineToRelative(0.015f, -0.04f)
                lineToRelative(0.013f, -0.04f)
                lineToRelative(0.013f, -0.04f)
                lineToRelative(0.012f, -0.042f)
                lineToRelative(0.011f, -0.039f)
                curveToRelative(0.0f, -0.015f, 0.007f, -0.029f, 0.01f, -0.044f)
                lineToRelative(0.008f, -0.038f)
                curveToRelative(0.0f, -0.015f, 0.006f, -0.031f, 0.008f, -0.047f)
                reflectiveCurveToRelative(0.0f, -0.025f, 0.006f, -0.037f)
                reflectiveCurveToRelative(0.0f, -0.033f, 0.006f, -0.05f)
                reflectiveCurveToRelative(0.0f, -0.023f, 0.0f, -0.035f)
                reflectiveCurveToRelative(0.0f, -0.038f, 0.0f, -0.057f)
                reflectiveCurveToRelative(0.0f, -0.019f, 0.0f, -0.029f)
                curveToRelative(0.0f, -0.029f, 0.0f, -0.058f, 0.0f, -0.088f)
                reflectiveCurveToRelative(0.0f, -0.058f, 0.0f, -0.087f)
                curveToRelative(0.0f, -0.01f, 0.0f, -0.019f, 0.0f, -0.028f)
                reflectiveCurveToRelative(0.0f, -0.038f, 0.0f, -0.057f)
                reflectiveCurveToRelative(0.0f, -0.023f, 0.0f, -0.034f)
                reflectiveCurveToRelative(0.0f, -0.034f, -0.006f, -0.05f)
                reflectiveCurveToRelative(0.0f, -0.024f, -0.006f, -0.036f)
                reflectiveCurveToRelative(-0.005f, -0.032f, -0.008f, -0.047f)
                reflectiveCurveToRelative(0.0f, -0.025f, -0.008f, -0.037f)
                reflectiveCurveToRelative(-0.007f, -0.03f, -0.01f, -0.045f)
                reflectiveCurveToRelative(-0.006f, -0.025f, -0.01f, -0.037f)
                lineToRelative(-0.012f, -0.043f)
                lineToRelative(-0.012f, -0.037f)
                lineToRelative(-0.014f, -0.041f)
                lineToRelative(-0.014f, -0.037f)
                lineToRelative(-0.015f, -0.039f)
                lineToRelative(-0.016f, -0.038f)
                lineToRelative(-0.017f, -0.037f)
                lineToRelative(-0.018f, -0.038f)
                lineToRelative(-0.018f, -0.034f)
                lineToRelative(-0.02f, -0.038f)
                lineToRelative(-0.018f, -0.031f)
                lineToRelative(-0.023f, -0.04f)
                lineToRelative(-0.016f, -0.025f)
                lineToRelative(-0.028f, -0.044f)
                lineToRelative(0.0f, 0.0f)
                arcToRelative(1.738f, 1.738f, 0.0f, false, false, -0.135f, -0.173f)
                lineToRelative(-0.007f, -0.009f)
                lineToRelative(-0.043f, -0.045f)
                lineToRelative(-0.012f, -0.013f)
                lineToRelative(-0.043f, -0.042f)
                lineToRelative(-0.014f, -0.013f)
                lineToRelative(-0.045f, -0.04f)
                lineToRelative(-0.014f, -0.012f)
                lineToRelative(-0.047f, -0.038f)
                lineToRelative(-0.014f, -0.011f)
                lineToRelative(-0.049f, -0.037f)
                lineToRelative(-0.014f, -0.01f)
                lineToRelative(-0.052f, -0.036f)
                lineToRelative(-0.012f, -0.008f)
                lineToRelative(-0.056f, -0.034f)
                lineToRelative(-0.009f, -0.005f)
                lineToRelative(-0.061f, -0.034f)
                lineToRelative(0.0f, 0.0f)
                arcToRelative(1.732f, 1.732f, 0.0f, false, false, -0.807f, -0.2f)
                horizontalLineToRelative(-8.49f)
                arcToRelative(1.766f, 1.766f, 0.0f, false, false, -0.311f, 0.028f)
                horizontalLineToRelative(-0.005f)
                arcToRelative(1.727f, 1.727f, 0.0f, false, false, -0.313f, 0.088f)
                horizontalLineToRelative(0.0f)
                curveToRelative(-0.033f, 0.013f, -0.065f, 0.026f, -0.1f, 0.041f)
                horizontalLineToRelative(0.0f)
                arcToRelative(1.73f, 1.73f, 0.0f, false, false, -0.276f, 0.16f)
                lineToRelative(0.0f, 0.0f)
                arcToRelative(1.739f, 1.739f, 0.0f, false, false, -0.24f, 0.206f)
                horizontalLineToRelative(0.0f)
                quadToRelative(-0.036f, 0.037f, -0.07f, 0.076f)
                lineToRelative(0.0f, 0.0f)
                arcToRelative(1.744f, 1.744f, 0.0f, false, false, -0.184f, 0.259f)
                lineToRelative(0.0f, 0.0f)
                arcToRelative(1.737f, 1.737f, 0.0f, false, false, -0.159f, 0.367f)
                verticalLineToRelative(0.0f)
                curveToRelative(-0.008f, 0.028f, -0.016f, 0.057f, -0.023f, 0.086f)
                lineToRelative(0.0f, 0.021f)
                curveToRelative(-0.005f, 0.024f, -0.01f, 0.047f, -0.015f, 0.071f)
                reflectiveCurveToRelative(0.0f, 0.019f, 0.0f, 0.028f)
                reflectiveCurveToRelative(-0.007f, 0.044f, -0.01f, 0.067f)
                reflectiveCurveToRelative(0.0f, 0.021f, 0.0f, 0.031f)
                reflectiveCurveToRelative(0.0f, 0.046f, -0.006f, 0.069f)
                reflectiveCurveToRelative(0.0f, 0.019f, 0.0f, 0.029f)
                curveToRelative(0.0f, 0.032f, 0.0f, 0.065f, 0.0f, 0.1f)
                reflectiveCurveToRelative(0.0f, 0.066f, 0.0f, 0.1f)
                curveToRelative(0.0f, 0.01f, 0.0f, 0.019f, 0.0f, 0.029f)
                reflectiveCurveToRelative(0.0f, 0.046f, 0.006f, 0.069f)
                lineToRelative(0.0f, 0.031f)
                curveToRelative(0.0f, 0.023f, 0.006f, 0.046f, 0.01f, 0.069f)
                reflectiveCurveToRelative(0.0f, 0.018f, 0.0f, 0.027f)
                curveToRelative(0.0f, 0.025f, 0.01f, 0.051f, 0.016f, 0.076f)
                lineToRelative(0.0f, 0.017f)
                arcToRelative(1.745f, 1.745f, 0.0f, false, false, 1.694f, 1.329f)
                horizontalLineToRelative(2.5f)
                verticalLineToRelative(8.374f)
                arcToRelative(1.744f, 1.744f, 0.0f, false, false, 0.018f, 0.252f)
                arcToRelative(1.731f, 1.731f, 0.0f, false, false, 0.085f, 0.34f)
                horizontalLineToRelative(0.0f)
                curveToRelative(0.009f, 0.026f, 0.019f, 0.051f, 0.03f, 0.077f)
                lineToRelative(0.0f, 0.0f)
                curveToRelative(0.01f, 0.024f, 0.021f, 0.048f, 0.032f, 0.072f)
                lineToRelative(0.0f, 0.006f)
                curveToRelative(0.011f, 0.024f, 0.023f, 0.047f, 0.036f, 0.07f)
                lineToRelative(0.0f, 0.006f)
                curveToRelative(0.013f, 0.025f, 0.027f, 0.049f, 0.042f, 0.073f)
                horizontalLineToRelative(0.0f)
                curveToRelative(0.021f, 0.035f, 0.043f, 0.068f, 0.066f, 0.1f)
                lineToRelative(0.012f, 0.016f)
                arcToRelative(1.752f, 1.752f, 0.0f, false, false, 0.57f, 0.507f)
                horizontalLineToRelative(0.0f)
                curveToRelative(0.04f, 0.022f, 0.081f, 0.043f, 0.123f, 0.062f)
                lineToRelative(0.009f, 0.0f)
                curveToRelative(0.042f, 0.019f, 0.086f, 0.036f, 0.13f, 0.052f)
                arcToRelative(1.733f, 1.733f, 0.0f, false, false, 0.521f, 0.1f)
                lineToRelative(0.06f, 0.0f)
                curveToRelative(0.034f, 0.0f, 0.067f, 0.0f, 0.1f, 0.0f)
                lineToRelative(0.031f, 0.0f)
                lineToRelative(0.069f, -0.006f)
                lineToRelative(0.035f, 0.0f)
                lineToRelative(0.066f, -0.01f)
                lineToRelative(0.032f, -0.006f)
                lineTo(34.4f, 16.567f)
                lineToRelative(0.021f, 0.0f)
                lineToRelative(0.1f, -0.027f)
                horizontalLineToRelative(0.0f)
                lineToRelative(0.087f, -0.03f)
                lineToRelative(0.021f, -0.008f)
                lineToRelative(0.07f, -0.029f)
                lineToRelative(0.021f, -0.01f)
                lineToRelative(0.072f, -0.035f)
                lineTo(34.8f, 16.415f)
                arcToRelative(1.751f, 1.751f, 0.0f, false, false, 0.25f, -0.161f)
                lineToRelative(0.005f, 0.0f)
                quadToRelative(0.037f, -0.029f, 0.073f, -0.06f)
                lineToRelative(0.0f, 0.0f)
                arcToRelative(1.752f, 1.752f, 0.0f, false, false, 0.413f, -0.532f)
                horizontalLineToRelative(0.0f)
                arcToRelative(1.726f, 1.726f, 0.0f, false, false, 0.135f, -0.367f)
                lineToRelative(0.0f, -0.019f)
                curveToRelative(0.006f, -0.024f, 0.011f, -0.049f, 0.015f, -0.073f)
                lineToRelative(0.005f, -0.027f)
                curveToRelative(0.0f, -0.022f, 0.007f, -0.045f, 0.01f, -0.068f)
                reflectiveCurveToRelative(0.0f, -0.02f, 0.0f, -0.031f)
                reflectiveCurveToRelative(0.0f, -0.046f, 0.006f, -0.069f)
                reflectiveCurveToRelative(0.0f, -0.019f, 0.0f, -0.029f)
                curveToRelative(0.0f, -0.032f, 0.0f, -0.065f, 0.0f, -0.1f)
                verticalLineToRelative(-8.374f)
                horizontalLineToRelative(2.5f)
                curveToRelative(0.042f, 0.0f, 0.084f, 0.0f, 0.126f, -0.005f)
                arcToRelative(1.737f, 1.737f, 0.0f, false, false, 1.031f, -0.435f)
                lineToRelative(0.0f, 0.0f)
                lineToRelative(0.051f, -0.047f)
                lineToRelative(0.009f, -0.009f)
                lineToRelative(0.045f, -0.045f)
                close()
                moveTo(49.91f, 13.126f)
                horizontalLineToRelative(-5.0f)
                lineTo(44.91f, 11.558f)
                horizontalLineToRelative(0.636f)
                verticalLineToRelative(0.0f)
                horizontalLineToRelative(3.376f)
                arcToRelative(1.744f, 1.744f, 0.0f, false, false, 1.733f, -1.55f)
                arcToRelative(1.749f, 1.749f, 0.0f, false, false, 0.011f, -0.195f)
                arcToRelative(1.736f, 1.736f, 0.0f, false, false, -0.254f, -0.906f)
                lineToRelative(0.0f, -0.006f)
                arcToRelative(1.752f, 1.752f, 0.0f, false, false, -0.765f, -0.676f)
                horizontalLineToRelative(0.0f)
                curveToRelative(-0.037f, -0.017f, -0.075f, -0.033f, -0.113f, -0.047f)
                lineToRelative(-0.016f, -0.006f)
                horizontalLineToRelative(0.0f)
                arcToRelative(1.743f, 1.743f, 0.0f, false, false, -0.588f, -0.1f)
                lineTo(44.909f, 8.072f)
                verticalLineToRelative(-1.57f)
                horizontalLineToRelative(5.0f)
                arcToRelative(1.744f, 1.744f, 0.0f, false, false, 1.744f, -1.745f)
                arcToRelative(1.745f, 1.745f, 0.0f, false, false, -1.744f, -1.745f)
                lineTo(43.165f, 3.012f)
                arcToRelative(1.745f, 1.745f, 0.0f, false, false, -1.745f, 1.745f)
                lineTo(41.42f, 14.87f)
                arcTo(1.745f, 1.745f, 0.0f, false, false, 43.165f, 16.615f)
                horizontalLineToRelative(6.746f)
                arcToRelative(1.745f, 1.745f, 0.0f, false, false, 1.744f, -1.745f)
                arcToRelative(1.744f, 1.744f, 0.0f, false, false, -1.744f, -1.745f)
                close()
            }
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(63.522f, 4.114f)
                arcToRelative(4.964f, 4.964f, 0.0f, false, false, -2.343f, -0.89f)
                curveToRelative(-0.149f, -0.016f, -0.3f, -0.027f, -0.452f, -0.029f)
                lineToRelative(-0.09f, 0.0f)
                lineTo(56.465f, 3.195f)
                lineToRelative(-0.085f, 0.0f)
                arcToRelative(1.745f, 1.745f, 0.0f, false, false, -1.66f, 1.742f)
                lineTo(54.72f, 15.055f)
                arcTo(1.745f, 1.745f, 0.0f, false, false, 56.465f, 16.8f)
                curveToRelative(0.037f, 0.0f, 0.073f, 0.0f, 0.109f, 0.0f)
                horizontalLineToRelative(0.011f)
                arcToRelative(1.739f, 1.739f, 0.0f, false, false, 1.139f, -0.533f)
                lineToRelative(0.0f, 0.0f)
                curveToRelative(0.02f, -0.021f, 0.04f, -0.042f, 0.058f, -0.064f)
                lineToRelative(0.0f, 0.0f)
                curveToRelative(0.019f, -0.022f, 0.037f, -0.044f, 0.055f, -0.067f)
                lineToRelative(0.0f, 0.0f)
                arcToRelative(1.739f, 1.739f, 0.0f, false, false, 0.283f, -0.538f)
                horizontalLineToRelative(0.0f)
                arcToRelative(1.693f, 1.693f, 0.0f, false, false, 0.044f, -0.168f)
                verticalLineToRelative(0.0f)
                curveToRelative(0.005f, -0.026f, 0.01f, -0.052f, 0.014f, -0.079f)
                curveToRelative(0.0f, 0.0f, 0.0f, -0.007f, 0.0f, -0.01f)
                curveToRelative(0.0f, -0.029f, 0.008f, -0.059f, 0.011f, -0.088f)
                horizontalLineToRelative(0.0f)
                arcToRelative(1.7f, 1.7f, 0.0f, false, false, 0.009f, -0.179f)
                lineTo(58.198f, 13.179f)
                horizontalLineToRelative(2.428f)
                lineToRelative(0.09f, 0.0f)
                arcToRelative(5.0f, 5.0f, 0.0f, false, false, 4.9f, -4.991f)
                arcTo(4.987f, 4.987f, 0.0f, false, false, 63.522f, 4.114f)
                close()
                moveTo(58.209f, 9.69f)
                verticalLineToRelative(-3.008f)
                horizontalLineToRelative(2.428f)
                arcToRelative(1.5f, 1.5f, 0.0f, false, true, 1.074f, 0.452f)
                arcToRelative(1.5f, 1.5f, 0.0f, false, true, 0.43f, 1.052f)
                arcToRelative(1.5f, 1.5f, 0.0f, false, true, -0.268f, 0.856f)
                arcToRelative(1.518f, 1.518f, 0.0f, false, true, -0.373f, 0.375f)
                arcToRelative(1.493f, 1.493f, 0.0f, false, true, -0.81f, 0.272f)
                lineTo(58.209f, 9.689f)
                close()
                moveTo(69.732f, 3.312f)
                arcToRelative(1.745f, 1.745f, 0.0f, false, false, -2.181f, 0.82f)
                arcToRelative(2.991f, 2.991f, 0.0f, false, false, -0.2f, 1.541f)
                verticalLineToRelative(8.717f)
                arcTo(2.7f, 2.7f, 0.0f, false, false, 67.589f, 15.93f)
                arcToRelative(1.752f, 1.752f, 0.0f, false, false, 2.143f, 0.751f)
                arcToRelative(1.864f, 1.864f, 0.0f, false, false, 0.722f, -0.527f)
                arcToRelative(1.743f, 1.743f, 0.0f, false, false, 0.389f, -1.1f)
                lineTo(70.843f, 4.937f)
                arcTo(1.746f, 1.746f, 0.0f, false, false, 69.732f, 3.311f)
                close()
                moveTo(84.617f, 12.695f)
                lineToRelative(-0.049f, -0.055f)
                arcToRelative(1.742f, 1.742f, 0.0f, false, false, -0.477f, -0.359f)
                arcToRelative(1.733f, 1.733f, 0.0f, false, false, -0.424f, -0.151f)
                curveToRelative(-0.038f, -0.008f, -0.075f, -0.015f, -0.113f, -0.021f)
                arcToRelative(1.741f, 1.741f, 0.0f, false, false, -1.452f, 0.453f)
                arcToRelative(3.5f, 3.5f, 0.0f, false, true, -1.537f, 0.842f)
                lineToRelative(-0.117f, 0.027f)
                curveToRelative(-0.094f, 0.02f, -0.188f, 0.037f, -0.283f, 0.05f)
                lineToRelative(-0.068f, 0.008f)
                arcToRelative(3.556f, 3.556f, 0.0f, false, true, -0.4f, 0.023f)
                arcToRelative(3.493f, 3.493f, 0.0f, false, true, -1.268f, -0.238f)
                quadToRelative(-0.094f, -0.037f, -0.185f, -0.078f)
                lineToRelative(-0.012f, -0.005f)
                quadToRelative(-0.092f, -0.042f, -0.181f, -0.089f)
                arcToRelative(3.53f, 3.53f, 0.0f, false, true, -0.633f, -0.433f)
                curveToRelative(-0.033f, -0.029f, -0.066f, -0.057f, -0.1f, -0.087f)
                arcToRelative(3.526f, 3.526f, 0.0f, false, true, -0.333f, -0.352f)
                curveToRelative(-0.042f, -0.051f, -0.082f, -0.1f, -0.122f, -0.157f)
                arcTo(3.491f, 3.491f, 0.0f, false, true, 76.226f, 10.5f)
                quadToRelative(-0.021f, -0.143f, -0.03f, -0.289f)
                curveToRelative(0.0f, -0.071f, -0.007f, -0.142f, -0.007f, -0.214f)
                arcToRelative(3.487f, 3.487f, 0.0f, false, true, 0.321f, -1.465f)
                arcToRelative(3.53f, 3.53f, 0.0f, false, true, 1.877f, -1.791f)
                arcToRelative(3.49f, 3.49f, 0.0f, false, true, 1.312f, -0.255f)
                arcToRelative(3.493f, 3.493f, 0.0f, false, true, 2.434f, 0.981f)
                arcTo(1.745f, 1.745f, 0.0f, false, false, 84.6f, 7.423f)
                arcToRelative(1.745f, 1.745f, 0.0f, false, false, -0.047f, -2.467f)
                arcToRelative(7.048f, 7.048f, 0.0f, false, false, -0.9f, -0.733f)
                arcTo(6.963f, 6.963f, 0.0f, false, false, 79.7f, 3.0f)
                arcToRelative(7.008f, 7.008f, 0.0f, false, false, -7.0f, 7.0f)
                arcToRelative(6.957f, 6.957f, 0.0f, false, false, 1.042f, 3.671f)
                arcToRelative(7.048f, 7.048f, 0.0f, false, false, 0.564f, 0.785f)
                horizontalLineToRelative(0.0f)
                curveToRelative(0.028f, 0.034f, 0.055f, 0.067f, 0.084f, 0.1f)
                quadToRelative(0.08f, 0.092f, 0.162f, 0.181f)
                lineToRelative(0.017f, 0.018f)
                arcToRelative(7.062f, 7.062f, 0.0f, false, false, 1.151f, 1.0f)
                quadToRelative(0.181f, 0.126f, 0.371f, 0.24f)
                horizontalLineToRelative(0.0f)
                quadToRelative(0.158f, 0.1f, 0.322f, 0.184f)
                lineToRelative(0.1f, 0.052f)
                horizontalLineToRelative(0.0f)
                quadToRelative(0.2f, 0.1f, 0.4f, 0.189f)
                horizontalLineToRelative(0.0f)
                arcTo(6.939f, 6.939f, 0.0f, false, false, 79.0f, 16.965f)
                lineToRelative(0.12f, 0.01f)
                curveToRelative(0.191f, 0.016f, 0.383f, 0.025f, 0.578f, 0.025f)
                arcTo(7.033f, 7.033f, 0.0f, false, false, 81.044f, 16.871f)
                quadToRelative(0.195f, -0.038f, 0.387f, -0.087f)
                horizontalLineToRelative(0.0f)
                arcToRelative(6.96f, 6.96f, 0.0f, false, false, 0.782f, -0.249f)
                lineToRelative(0.111f, -0.043f)
                arcToRelative(6.993f, 6.993f, 0.0f, false, false, 2.164f, -1.386f)
                arcToRelative(1.74f, 1.74f, 0.0f, false, false, 0.432f, -0.638f)
                horizontalLineToRelative(0.0f)
                arcToRelative(1.745f, 1.745f, 0.0f, false, false, -0.3f, -1.771f)
                close()
                moveTo(97.937f, 13.949f)
                lineTo(94.112f, 9.287f)
                lineToRelative(0.592f, -0.614f)
                lineToRelative(1.59f, -1.649f)
                lineToRelative(0.579f, -0.6f)
                lineToRelative(0.266f, -0.276f)
                arcToRelative(1.745f, 1.745f, 0.0f, false, false, -0.045f, -2.467f)
                arcToRelative(1.744f, 1.744f, 0.0f, false, false, -0.468f, -0.323f)
                lineToRelative(0.0f, 0.0f)
                arcToRelative(1.749f, 1.749f, 0.0f, false, false, -1.133f, -0.119f)
                horizontalLineToRelative(0.0f)
                arcToRelative(1.735f, 1.735f, 0.0f, false, false, -0.862f, 0.488f)
                lineToRelative(-0.316f, 0.327f)
                lineToRelative(-0.352f, 0.365f)
                lineToRelative(-0.832f, 0.863f)
                lineToRelative(-0.511f, 0.53f)
                lineToRelative(-1.96f, 2.032f)
                lineToRelative(-0.167f, 0.173f)
                verticalLineToRelative(-3.08f)
                arcToRelative(1.745f, 1.745f, 0.0f, false, false, -1.745f, -1.745f)
                arcTo(1.745f, 1.745f, 0.0f, false, false, 87.0f, 4.937f)
                lineTo(87.0f, 15.055f)
                curveToRelative(0.0f, 0.057f, 0.0f, 0.113f, 0.008f, 0.168f)
                curveToRelative(0.0f, 0.039f, 0.009f, 0.078f, 0.015f, 0.116f)
                lineToRelative(0.0f, 0.009f)
                quadToRelative(0.009f, 0.052f, 0.021f, 0.1f)
                lineToRelative(0.0f, 0.01f)
                arcTo(1.745f, 1.745f, 0.0f, false, false, 88.745f, 16.8f)
                arcToRelative(1.745f, 1.745f, 0.0f, false, false, 1.628f, -1.116f)
                arcToRelative(1.75f, 1.75f, 0.0f, false, false, 0.068f, -0.219f)
                lineToRelative(0.0f, -0.011f)
                quadToRelative(0.012f, -0.05f, 0.02f, -0.1f)
                lineToRelative(0.0f, -0.013f)
                curveToRelative(0.006f, -0.038f, 0.011f, -0.076f, 0.015f, -0.114f)
                curveToRelative(0.005f, -0.056f, 0.008f, -0.113f, 0.008f, -0.17f)
                lineTo(90.484f, 13.043f)
                lineToRelative(0.573f, -0.594f)
                lineToRelative(0.283f, -0.294f)
                lineToRelative(0.328f, -0.34f)
                lineTo(95.24f, 16.162f)
                arcTo(1.741f, 1.741f, 0.0f, false, false, 96.59f, 16.8f)
                arcTo(1.737f, 1.737f, 0.0f, false, false, 97.7f, 16.4f)
                arcToRelative(1.744f, 1.744f, 0.0f, false, false, 0.242f, -2.455f)
                close()
            }
        }
        .build()
        return _datepickLogo!!
    }

private var _datepickLogo: ImageVector? = null