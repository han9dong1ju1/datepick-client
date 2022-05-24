package app.hdj.datepick.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun BaseButton(
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    iconTint: Color? = null,
    text: String,
    shape: Shape = RoundedCornerShape(10.dp),
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    border: BorderStroke? = null,
    enabled: Boolean = true,
    elevation : Dp = 0.dp,
    onClick: () -> Unit = {},
) {
    CompositionLocalProvider(LocalElevationOverlay provides null) {
        Button(
            modifier = Modifier.then(modifier),
            onClick = onClick,
            shape = shape,
            enabled = enabled,
            colors = colors,
            border = border,
            elevation = ButtonDefaults.elevation(
                defaultElevation = elevation,
                pressedElevation = elevation,
                disabledElevation = elevation,
                hoveredElevation = elevation,
                focusedElevation = elevation,
            ),
        ) {

            icon?.let {
                Icon(
                    modifier = Modifier.size(ButtonDefaults.IconSize),
                    imageVector = it,
                    contentDescription = text,
                    tint = iconTint ?: LocalContentColor.current.copy(alpha = LocalContentAlpha.current)
                )
                Spacer(modifier = Modifier.width(10.dp))
            }

            Text(text)
        }
    }
}

@Composable
fun CallToActionButton(
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit = {},
) {
    BaseButton(
        modifier,
        icon,
        text = text,
        enabled = enabled,
        onClick = onClick
    )
}

@Composable
fun UnAccentButton(
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    text: String,
    shape: Shape = RoundedCornerShape(8.dp),
    enabled: Boolean = true,
    onClick: () -> Unit = {},
) {
    BaseButton(
        modifier,
        icon,
        text = text,
        shape = shape,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.onSurface.copy(alpha = 0.06f)
                .compositeOver(MaterialTheme.colors.surface),
            contentColor = MaterialTheme.colors.onSurface
                .copy(alpha = 0.4f)
                .compositeOver(MaterialTheme.colors.surface)
        ),
        enabled = enabled,
        onClick = onClick
    )
}

@Composable
fun TextButton(
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    text: String,
    textColor: Color = MaterialTheme.colors.secondary,
    shape: Shape = RoundedCornerShape(8.dp),
    enabled: Boolean = true,
    onClick: () -> Unit = {},
) {
    BaseButton(
        modifier,
        icon,
        text = text,
        shape = shape,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Transparent,
            contentColor = textColor
        ),
        enabled = enabled,
        onClick = onClick
    )
}