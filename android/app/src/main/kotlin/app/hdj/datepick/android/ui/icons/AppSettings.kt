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

public val DatePickIcons.AppSettings: ImageVector
    get() {
        if (_appSettings != null) {
            return _appSettings!!
        }
        _appSettings = Builder(name = "AppSettings", defaultWidth = 24.0.dp, defaultHeight =
        24.0.dp, viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            group {
                path(fill = SolidColor(Color(0xFF5c727c)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                    moveTo(19.5f, 12.0f)
                    curveToRelative(0.0f, -0.23f, -0.01f, -0.45f, -0.03f, -0.68f)
                    lineToRelative(1.86f, -1.41f)
                    arcToRelative(1.006f, 1.006f, 0.0f, false, false, 0.26f, -1.3f)
                    lineTo(19.72f, 5.38f)
                    arcToRelative(0.987f, 0.987f, 0.0f, false, false, -1.25f, -0.42f)
                    lineToRelative(-2.15f, 0.91f)
                    arcToRelative(7.587f, 7.587f, 0.0f, false, false, -1.17f, -0.68f)
                    lineToRelative(-0.29f, -2.31f)
                    arcTo(1.0f, 1.0f, 0.0f, false, false, 13.87f, 2.0f)
                    lineTo(10.14f, 2.0f)
                    arcToRelative(1.0f, 1.0f, 0.0f, false, false, -1.0f, 0.88f)
                    lineTo(8.85f, 5.19f)
                    arcToRelative(7.587f, 7.587f, 0.0f, false, false, -1.17f, 0.68f)
                    lineTo(5.53f, 4.96f)
                    arcToRelative(0.987f, 0.987f, 0.0f, false, false, -1.25f, 0.42f)
                    lineTo(2.41f, 8.62f)
                    arcToRelative(1.008f, 1.008f, 0.0f, false, false, 0.26f, 1.3f)
                    lineToRelative(1.86f, 1.41f)
                    curveToRelative(-0.02f, 0.22f, -0.03f, 0.44f, -0.03f, 0.67f)
                    reflectiveCurveToRelative(0.01f, 0.45f, 0.03f, 0.68f)
                    lineTo(2.67f, 14.09f)
                    arcToRelative(1.006f, 1.006f, 0.0f, false, false, -0.26f, 1.3f)
                    lineToRelative(1.87f, 3.23f)
                    arcToRelative(0.987f, 0.987f, 0.0f, false, false, 1.25f, 0.42f)
                    lineToRelative(2.15f, -0.91f)
                    arcToRelative(7.587f, 7.587f, 0.0f, false, false, 1.17f, 0.68f)
                    lineToRelative(0.29f, 2.31f)
                    arcToRelative(1.0f, 1.0f, 0.0f, false, false, 0.99f, 0.88f)
                    horizontalLineToRelative(3.73f)
                    arcToRelative(1.0f, 1.0f, 0.0f, false, false, 0.99f, -0.88f)
                    lineToRelative(0.29f, -2.31f)
                    arcToRelative(7.587f, 7.587f, 0.0f, false, false, 1.17f, -0.68f)
                    lineToRelative(2.15f, 0.91f)
                    arcToRelative(0.987f, 0.987f, 0.0f, false, false, 1.25f, -0.42f)
                    lineToRelative(1.87f, -3.23f)
                    arcToRelative(1.008f, 1.008f, 0.0f, false, false, -0.26f, -1.3f)
                    lineToRelative(-1.86f, -1.41f)
                    arcTo(5.17f, 5.17f, 0.0f, false, false, 19.5f, 12.0f)
                    close()
                    moveTo(12.04f, 15.5f)
                    arcToRelative(3.5f, 3.5f, 0.0f, true, true, 3.5f, -3.5f)
                    arcTo(3.5f, 3.5f, 0.0f, false, true, 12.04f, 15.5f)
                    close()
                }
                path(fill = SolidColor(Color(0xFF54666e)), stroke = SolidColor(Color(0x00000000)),
                    strokeLineWidth = 1.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f, pathFillType = NonZero) {
                    moveTo(12.0f, 12.0f)
                    moveToRelative(-4.0f, 0.0f)
                    arcToRelative(4.0f, 4.0f, 0.0f, true, true, 8.0f, 0.0f)
                    arcToRelative(4.0f, 4.0f, 0.0f, true, true, -8.0f, 0.0f)
                }
                path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF8ea2aa)),
                    strokeLineWidth = 1.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f, pathFillType = NonZero) {
                    moveTo(12.0f, 12.0f)
                    moveToRelative(-3.5f, 0.0f)
                    arcToRelative(3.5f, 3.5f, 0.0f, true, true, 7.0f, 0.0f)
                    arcToRelative(3.5f, 3.5f, 0.0f, true, true, -7.0f, 0.0f)
                }
            }
        }
            .build()
        return _appSettings!!
    }

private var _appSettings: ImageVector? = null
