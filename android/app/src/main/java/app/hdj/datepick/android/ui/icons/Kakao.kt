package app.hdj.datepick.android.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Kakao: ImageVector
    get() {
        if (_kakao != null) {
            return _kakao!!
        }
        _kakao = Builder(name = "Kakao", defaultWidth = 36.0.dp, defaultHeight = 36.0.dp,
            viewportWidth = 36.0f, viewportHeight = 36.0f).apply {
            group {
                path(fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.902f,
                    strokeAlpha = 0.902f, strokeLineWidth = 0.0f, strokeLineCap = Butt,
                    strokeLineJoin = Miter, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                    moveTo(18.0f, 1.936f)
                    curveToRelative(-9.942f, 0.0f, -18.0f, 6.256f, -18.0f, 13.973f)
                    curveToRelative(0.0f, 4.8f, 3.117f, 9.03f, 7.863f, 11.546f)
                    lineToRelative(-2.0f, 7.33f)
                    arcToRelative(0.736f, 0.736f, 0.0f, false, false, 1.127f, 0.79f)
                    lineToRelative(8.754f, -5.806f)
                    curveToRelative(0.739f, 0.072f, 1.49f, 0.113f, 2.253f, 0.113f)
                    curveToRelative(9.941f, 0.0f, 18.0f, -6.256f, 18.0f, -13.972f)
                    reflectiveCurveToRelative(-8.059f, -13.973f, -18.0f, -13.973f)
                }
            }
        }
            .build()
        return _kakao!!
    }

private var _kakao: ImageVector? = null