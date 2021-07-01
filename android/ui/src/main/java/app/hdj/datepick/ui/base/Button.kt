package app.hdj.datepick.ui.base

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun DatePickButton(
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    text: String,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    enabled: Boolean = true,
    onClick: () -> Unit = {},
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled,
        colors = colors,
        elevation = ButtonDefaults.elevation(0.dp, 0.dp, 0.dp),
    ) {

        icon?.let {
            Icon(imageVector = it, contentDescription = text)
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
        modifier, icon, text,
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
        modifier, icon, text,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.onSurface.copy(alpha = 0.08f)
                .compositeOver(MaterialTheme.colors.surface),
            contentColor = MaterialTheme.colors.onSurface
                .copy(alpha = 0.8f)
                .compositeOver(MaterialTheme.colors.surface)
        ),
        enabled = enabled,
        onClick = onClick
    )
}