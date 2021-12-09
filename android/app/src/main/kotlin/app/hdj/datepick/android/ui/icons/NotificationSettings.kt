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

public val DatePickIcons.NotificationSettings: ImageVector
    get() {
        if (_notificationSettings != null) {
            return _notificationSettings!!
        }
        _notificationSettings = Builder(name = "NotificationSettings", defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp, viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            group {
                path(fill = SolidColor(Color(0xFFe0ba53)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                    moveTo(18.0f, 17.0f)
                    verticalLineTo(10.0f)
                    arcToRelative(5.987f, 5.987f, 0.0f, false, false, -4.5f, -5.8f)
                    verticalLineTo(3.5f)
                    arcToRelative(1.5f, 1.5f, 0.0f, false, false, -3.0f, 0.0f)
                    verticalLineToRelative(0.7f)
                    arcTo(5.987f, 5.987f, 0.0f, false, false, 6.0f, 10.0f)
                    verticalLineToRelative(7.0f)
                    horizontalLineTo(5.0f)
                    arcToRelative(1.0f, 1.0f, 0.0f, false, false, -1.0f, 1.0f)
                    horizontalLineTo(4.0f)
                    arcToRelative(1.0f, 1.0f, 0.0f, false, false, 1.0f, 1.0f)
                    horizontalLineTo(19.0f)
                    arcToRelative(1.0f, 1.0f, 0.0f, false, false, 1.0f, -1.0f)
                    horizontalLineToRelative(0.0f)
                    arcToRelative(1.0f, 1.0f, 0.0f, false, false, -1.0f, -1.0f)
                    close()
                }
                path(fill = SolidColor(Color(0xFFe5c264)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                    moveTo(5.0f, 19.0f)
                    arcToRelative(1.0f, 1.0f, 0.0f, false, true, -1.0f, -1.0f)
                    arcToRelative(1.0f, 1.0f, 0.0f, false, true, 1.0f, -1.0f)
                    horizontalLineToRelative(1.0f)
                    verticalLineToRelative(-7.0f)
                    arcToRelative(5.986f, 5.986f, 0.0f, false, true, 4.5f, -5.8f)
                    verticalLineToRelative(-0.7f)
                    arcToRelative(1.5f, 1.5f, 0.0f, false, true, 1.5f, -1.5f)
                    verticalLineToRelative(17.0f)
                    close()
                }
                path(fill = SolidColor(Color(0xFFc4a140)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                    moveTo(12.0f, 22.0f)
                    arcToRelative(2.006f, 2.006f, 0.0f, false, false, 2.0f, -2.0f)
                    horizontalLineTo(10.0f)
                    arcTo(2.006f, 2.006f, 0.0f, false, false, 12.0f, 22.0f)
                    close()
                }
                path(fill = SolidColor(Color(0xFF5c727c)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                    moveTo(21.125f, 18.0f)
                    curveToRelative(0.0f, -0.1f, 0.0f, -0.187f, -0.013f, -0.283f)
                    lineToRelative(0.775f, -0.587f)
                    arcToRelative(0.419f, 0.419f, 0.0f, false, false, 0.108f, -0.542f)
                    lineTo(21.217f, 15.241f)
                    arcToRelative(0.411f, 0.411f, 0.0f, false, false, -0.521f, -0.175f)
                    lineToRelative(-0.9f, 0.379f)
                    arcToRelative(3.161f, 3.161f, 0.0f, false, false, -0.488f, -0.283f)
                    lineTo(19.192f, 14.2f)
                    arcTo(0.418f, 0.418f, 0.0f, false, false, 18.779f, 13.833f)
                    lineTo(17.225f, 13.833f)
                    arcToRelative(0.419f, 0.419f, 0.0f, false, false, -0.417f, 0.367f)
                    lineToRelative(-0.121f, 0.963f)
                    arcToRelative(3.161f, 3.161f, 0.0f, false, false, -0.488f, 0.283f)
                    lineToRelative(-0.9f, -0.379f)
                    arcToRelative(0.411f, 0.411f, 0.0f, false, false, -0.521f, 0.175f)
                    lineToRelative(-0.779f, 1.35f)
                    arcToRelative(0.42f, 0.42f, 0.0f, false, false, 0.108f, 0.542f)
                    lineToRelative(0.775f, 0.588f)
                    curveToRelative(-0.008f, 0.092f, -0.013f, 0.183f, -0.013f, 0.279f)
                    reflectiveCurveToRelative(0.0f, 0.187f, 0.013f, 0.283f)
                    lineToRelative(-0.775f, 0.588f)
                    arcToRelative(0.419f, 0.419f, 0.0f, false, false, -0.108f, 0.542f)
                    lineToRelative(0.779f, 1.346f)
                    arcToRelative(0.411f, 0.411f, 0.0f, false, false, 0.521f, 0.175f)
                    lineToRelative(0.9f, -0.379f)
                    arcTo(3.161f, 3.161f, 0.0f, false, false, 16.688f, 20.833f)
                    lineToRelative(0.121f, 0.962f)
                    arcToRelative(0.418f, 0.418f, 0.0f, false, false, 0.412f, 0.367f)
                    lineTo(18.772f, 22.162f)
                    arcToRelative(0.418f, 0.418f, 0.0f, false, false, 0.413f, -0.367f)
                    lineTo(19.308f, 20.833f)
                    arcToRelative(3.161f, 3.161f, 0.0f, false, false, 0.488f, -0.283f)
                    lineToRelative(0.9f, 0.379f)
                    arcToRelative(0.411f, 0.411f, 0.0f, false, false, 0.521f, -0.175f)
                    lineToRelative(0.779f, -1.346f)
                    arcToRelative(0.42f, 0.42f, 0.0f, false, false, -0.108f, -0.542f)
                    lineTo(21.108f, 18.283f)
                    arcTo(2.154f, 2.154f, 0.0f, false, false, 21.125f, 18.0f)
                    close()
                    moveTo(18.017f, 19.458f)
                    arcTo(1.458f, 1.458f, 0.0f, true, true, 19.472f, 18.0f)
                    arcTo(1.46f, 1.46f, 0.0f, false, true, 18.017f, 19.458f)
                    close()
                }
                path(fill = SolidColor(Color(0xFF54666e)), stroke = SolidColor(Color(0x00000000)),
                    strokeLineWidth = 0.5f, strokeLineCap = Butt, strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f, pathFillType = NonZero) {
                    moveTo(18.0f, 18.0f)
                    moveToRelative(-1.667f, 0.0f)
                    arcToRelative(1.667f, 1.667f, 0.0f, true, true, 3.334f, 0.0f)
                    arcToRelative(1.667f, 1.667f, 0.0f, true, true, -3.334f, 0.0f)
                }
                path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF8ea2aa)),
                    strokeLineWidth = 0.5f, strokeLineCap = Butt, strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f, pathFillType = NonZero) {
                    moveTo(18.0f, 18.0f)
                    moveToRelative(-1.417f, 0.0f)
                    arcToRelative(1.417f, 1.417f, 0.0f, true, true, 2.834f, 0.0f)
                    arcToRelative(1.417f, 1.417f, 0.0f, true, true, -2.834f, 0.0f)
                }
            }
        }
            .build()
        return _notificationSettings!!
    }

private var _notificationSettings: ImageVector? = null