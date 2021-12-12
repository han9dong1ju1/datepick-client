package app.hdj.datepick.android.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeCap.Companion.Round
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val DatePickIcons.DatePickIcon: ImageVector
    get() {
        if (_datePickIcon != null) {
            return _datePickIcon!!
        }
        _datePickIcon = Builder(name = "DatePickIcon", defaultWidth = 72.0.dp, defaultHeight = 72.0.dp,
            viewportWidth = 72.0f, viewportHeight = 72.0f).apply {
            group {
                path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF6a4a4a)),
                    strokeLineWidth = 8.0f, strokeLineCap = Round, strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f, pathFillType = NonZero) {
                    moveTo(44.562f, 12.435f)
                    reflectiveCurveTo(62.852f, 24.133f, 58.194f, 41.519f)
                }
                path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF6a4a4a)),
                    strokeLineWidth = 8.0f, strokeLineCap = Round, strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f, pathFillType = NonZero) {
                    moveTo(44.324f, 12.92f)
                    reflectiveCurveTo(49.134f, 28.976f, 27.013f, 41.747f)
                }
                path(fill = SolidColor(Color(0xFFe24b68)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                    moveTo(52.755f, 38.792f)
                    arcTo(14.682f, 14.682f, 0.0f, true, true, 38.073f, 53.474f)
                    arcTo(14.682f, 14.682f, 0.0f, false, true, 52.755f, 38.792f)
                    close()
                }
                path(fill = SolidColor(Color(0xFFFFFFFF)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                    moveTo(47.232f, 46.808f)
                    moveToRelative(-3.929f, 0.0f)
                    arcToRelative(3.929f, 3.929f, 0.0f, true, true, 7.858f, 0.0f)
                    arcToRelative(3.929f, 3.929f, 0.0f, true, true, -7.858f, 0.0f)
                }
                path(fill = SolidColor(Color(0xFFe24b68)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                    moveTo(19.245f, 34.228f)
                    arcTo(14.682f, 14.682f, 0.0f, true, true, 4.563f, 48.91f)
                    arcTo(14.682f, 14.682f, 0.0f, false, true, 19.245f, 34.228f)
                    close()
                }
                path(fill = SolidColor(Color(0xFFFFFFFF)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                    moveTo(13.722f, 42.244f)
                    moveToRelative(-3.929f, 0.0f)
                    arcToRelative(3.929f, 3.929f, 0.0f, true, true, 7.858f, 0.0f)
                    arcToRelative(3.929f, 3.929f, 0.0f, true, true, -7.858f, 0.0f)
                }
                path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF533a3a)),
                    strokeLineWidth = 8.0f, strokeLineCap = Round, strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f, pathFillType = NonZero) {
                    moveTo(26.553f, 18.369f)
                    reflectiveCurveTo(43.1397f, 2.3962f, 62.3047f, 13.4646f)
                }
            }
        }
            .build()
        return _datePickIcon!!
    }

private var _datePickIcon: ImageVector? = null
