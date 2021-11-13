package app.hdj.datepick.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun BaseButton(
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
        modifier = modifier.height(54.dp),
        onClick = onClick,
        enabled = enabled,
        colors = colors,
        border = border,
        elevation = ButtonDefaults.buttonElevation(0.dp, 0.dp, 0.dp),
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
        colors = ButtonDefaults.buttonColors(),
        enabled = enabled,
        onClick = onClick
    )
}

@Composable
fun UnAccentButton(
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
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.06f)
                .compositeOver(MaterialTheme.colorScheme.surface),
            contentColor = MaterialTheme.colorScheme.onSurface
                .copy(alpha = 0.8f)
                .compositeOver(MaterialTheme.colorScheme.surface)
        ),
        enabled = enabled,
        onClick = onClick
    )
}