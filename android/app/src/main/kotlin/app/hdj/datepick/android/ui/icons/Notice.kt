package app.hdj.datepick.android.ui.icons


import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val DatePickIcons.Notice: ImageVector
    get() {
        if (_notice != null) {
            return _notice!!
        }
        _notice = Builder(name = "Notice", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
            viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            group {
                path(fill = SolidColor(Color(0xFFe5c264)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                    moveTo(18.0f, 12.0f)
                    horizontalLineToRelative(0.0f)
                    arcToRelative(1.0f, 1.0f, 0.0f, false, false, 1.0f, 1.0f)
                    horizontalLineToRelative(2.0f)
                    arcToRelative(1.0f, 1.0f, 0.0f, false, false, 1.0f, -1.0f)
                    horizontalLineToRelative(0.0f)
                    arcToRelative(1.0f, 1.0f, 0.0f, false, false, -1.0f, -1.0f)
                    horizontalLineTo(19.0f)
                    arcTo(1.0f, 1.0f, 0.0f, false, false, 18.0f, 12.0f)
                    close()
                }
                path(fill = SolidColor(Color(0xFFe5c264)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                    moveTo(16.59f, 16.82f)
                    arcToRelative(0.966f, 0.966f, 0.0f, false, false, 0.2f, 1.37f)
                    curveToRelative(0.53f, 0.39f, 1.09f, 0.81f, 1.62f, 1.21f)
                    arcToRelative(0.978f, 0.978f, 0.0f, false, false, 1.38f, -0.2f)
                    curveToRelative(0.0f, -0.01f, 0.01f, -0.01f, 0.01f, -0.02f)
                    arcToRelative(0.978f, 0.978f, 0.0f, false, false, -0.2f, -1.38f)
                    curveToRelative(-0.53f, -0.4f, -1.09f, -0.82f, -1.61f, -1.21f)
                    arcToRelative(0.993f, 0.993f, 0.0f, false, false, -1.39f, 0.21f)
                    arcTo(0.035f, 0.035f, 0.0f, false, true, 16.59f, 16.82f)
                    close()
                }
                path(fill = SolidColor(Color(0xFFe5c264)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                    moveTo(19.81f, 4.81f)
                    curveToRelative(0.0f, -0.01f, -0.01f, -0.01f, -0.01f, -0.02f)
                    arcToRelative(0.98f, 0.98f, 0.0f, false, false, -1.38f, -0.2f)
                    curveToRelative(-0.53f, 0.4f, -1.1f, 0.82f, -1.62f, 1.22f)
                    arcToRelative(0.979f, 0.979f, 0.0f, false, false, -0.19f, 1.38f)
                    curveToRelative(0.0f, 0.01f, 0.01f, 0.01f, 0.01f, 0.02f)
                    arcToRelative(0.979f, 0.979f, 0.0f, false, false, 1.38f, 0.2f)
                    curveToRelative(0.53f, -0.39f, 1.09f, -0.82f, 1.62f, -1.22f)
                    arcTo(0.994f, 0.994f, 0.0f, false, false, 19.81f, 4.81f)
                    close()
                }
                path(fill = SolidColor(Color(0xFF549ce5)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                    moveTo(8.0f, 9.0f)
                    horizontalLineTo(4.0f)
                    arcToRelative(2.006f, 2.006f, 0.0f, false, false, -2.0f, 2.0f)
                    verticalLineToRelative(2.0f)
                    arcToRelative(2.006f, 2.006f, 0.0f, false, false, 2.0f, 2.0f)
                    horizontalLineTo(5.0f)
                    verticalLineToRelative(3.0f)
                    arcToRelative(1.0f, 1.0f, 0.0f, false, false, 1.0f, 1.0f)
                    horizontalLineTo(6.0f)
                    arcToRelative(1.0f, 1.0f, 0.0f, false, false, 1.0f, -1.0f)
                    verticalLineTo(15.0f)
                    horizontalLineTo(8.0f)
                    lineToRelative(5.0f, 3.0f)
                    verticalLineTo(6.0f)
                    close()
                }
                path(fill = SolidColor(Color(0xFF83b3e5)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                    moveTo(15.5f, 12.0f)
                    arcTo(4.48f, 4.48f, 0.0f, false, false, 14.0f, 8.65f)
                    verticalLineToRelative(6.69f)
                    arcTo(4.442f, 4.442f, 0.0f, false, false, 15.5f, 12.0f)
                    close()
                }
                path(fill = SolidColor(Color(0xFF4682bf)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                    moveTo(13.0f, 6.0f)
                    lineTo(13.0f, 6.0f)
                    arcTo(1.0f, 1.0f, 0.0f, false, true, 14.0f, 7.0f)
                    lineTo(14.0f, 17.0f)
                    arcTo(1.0f, 1.0f, 0.0f, false, true, 13.0f, 18.0f)
                    lineTo(13.0f, 18.0f)
                    arcTo(1.0f, 1.0f, 0.0f, false, true, 12.0f, 17.0f)
                    lineTo(12.0f, 7.0f)
                    arcTo(1.0f, 1.0f, 0.0f, false, true, 13.0f, 6.0f)
                    close()
                }
            }
        }
            .build()
        return _notice!!
    }

private var _notice: ImageVector? = null