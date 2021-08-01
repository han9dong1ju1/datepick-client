package app.hdj.datepick.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun DatePickButton(
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    iconTint: Color? = null,
    text: String,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    border: BorderStroke? = null,
    enabled: Boolean = true,
    onClick: () -> Unit = {},
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled,
        colors = colors,
        border = border,
        elevation = ButtonDefaults.elevation(0.dp, 0.dp, 0.dp),
    ) {

        icon?.let {
            Icon(
                modifier = Modifier.size(ButtonDefaults.IconSize),
                imageVector = it,
                contentDescription = text,
                tint = iconTint ?: LocalContentColor.current.copy(alpha = LocalContentAlpha.current)
            )
            Spacer(modifier = Modifier.width(ButtonDefaults.IconSpacing))
        }

        Text(text)
    }
}

@Composable
fun DatePickCTAButton(
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit = {},
) {
    DatePickButton(
        modifier,
        icon,
        text = text,
        colors = ButtonDefaults.buttonColors(),
        enabled = enabled,
        onClick = onClick
    )
}

@Composable
fun DatePickUnAccentButton(
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit = {},
) {
    DatePickButton(
        modifier,
        icon,
        text = text,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.onSurface.copy(alpha = 0.06f)
                .compositeOver(MaterialTheme.colors.surface),
            contentColor = MaterialTheme.colors.onSurface
                .copy(alpha = 0.8f)
                .compositeOver(MaterialTheme.colors.surface)
        ),
        enabled = enabled,
        onClick = onClick
    )
}