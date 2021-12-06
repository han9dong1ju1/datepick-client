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

val DatePickIcons.Diary: ImageVector
    get() {
        if (_diary != null) {
            return _diary!!
        }
        _diary = Builder(name = "Diary", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
            viewportWidth = 24.0f, viewportHeight = 24.0f).apply {
            group {
                path(fill = SolidColor(Color(0xFFefedee)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                    moveTo(6.865f, 17.359f)
                    arcToRelative(1.62f, 1.62f, 0.0f, false, false, 0.0f, 3.241f)
                    lineTo(19.323f, 20.6f)
                    verticalLineToRelative(-3.241f)
                    close()
                    moveTo(6.865f, 17.359f)
                }
                path(fill = SolidColor(Color(0xFFd9d7d8)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                    moveTo(6.497f, 18.979f)
                    arcToRelative(1.62f, 1.62f, 0.0f, false, true, 1.62f, -1.62f)
                    lineTo(6.865f, 17.359f)
                    arcToRelative(1.62f, 1.62f, 0.0f, false, false, 0.0f, 3.241f)
                    horizontalLineToRelative(1.251f)
                    arcTo(1.62f, 1.62f, 0.0f, false, true, 6.497f, 18.979f)
                    close()
                    moveTo(6.497f, 18.979f)
                }
                path(fill = SolidColor(Color(0xFFfd6f71)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                    moveTo(6.865f, 20.6f)
                    arcToRelative(1.62f, 1.62f, 0.0f, false, true, 0.0f, -3.241f)
                    lineTo(19.456f, 17.359f)
                    arcToRelative(0.7f, 0.7f, 0.0f, false, false, 0.7f, -0.7f)
                    lineTo(20.156f, 2.7f)
                    arcToRelative(0.7f, 0.7f, 0.0f, false, false, -0.7f, -0.7f)
                    lineTo(6.653f, 2.0f)
                    arcTo(2.809f, 2.809f, 0.0f, false, false, 3.844f, 4.809f)
                    lineTo(3.844f, 19.191f)
                    arcTo(2.809f, 2.809f, 0.0f, false, false, 6.653f, 22.0f)
                    horizontalLineToRelative(12.8f)
                    arcToRelative(0.7f, 0.7f, 0.0f, false, false, 0.0f, -1.4f)
                    close()
                    moveTo(6.865f, 20.6f)
                }
                path(fill = SolidColor(Color(0xFFe36465)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                    moveTo(19.456f, 20.6f)
                    lineTo(6.865f, 20.6f)
                    arcToRelative(1.62f, 1.62f, 0.0f, false, true, 0.0f, -3.241f)
                    horizontalLineToRelative(0.818f)
                    lineTo(7.683f, 2.0f)
                    lineTo(6.653f, 2.0f)
                    arcTo(2.809f, 2.809f, 0.0f, false, false, 3.844f, 4.809f)
                    lineTo(3.844f, 19.191f)
                    arcTo(2.809f, 2.809f, 0.0f, false, false, 6.653f, 22.0f)
                    horizontalLineToRelative(12.8f)
                    arcToRelative(0.7f, 0.7f, 0.0f, false, false, 0.0f, -1.4f)
                    close()
                    moveTo(19.456f, 20.6f)
                }
                path(fill = SolidColor(Color(0x00FFFFFF)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                    moveTo(0.0f, 0.0f)
                    horizontalLineToRelative(24.0f)
                    verticalLineToRelative(24.0f)
                    horizontalLineToRelative(-24.0f)
                    close()
                }
            }
        }
            .build()
        return _diary!!
    }

private var _diary: ImageVector? = null