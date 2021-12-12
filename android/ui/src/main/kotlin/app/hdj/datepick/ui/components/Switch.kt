package app.hdj.datepick.ui.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.ContentAlpha
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.SwitchColors
import androidx.compose.material.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver

@Composable
fun SwitchDefaults.material3Colors(
    checkedThumbColor: Color = MaterialTheme.colorScheme.secondaryContainer,
    checkedTrackColor: Color = checkedThumbColor,
    checkedTrackAlpha: Float = 0.54f,
    uncheckedThumbColor: Color = MaterialTheme.colorScheme.surface,
    uncheckedTrackColor: Color = MaterialTheme.colorScheme.onSurface,
    uncheckedTrackAlpha: Float = 0.38f,
    disabledCheckedThumbColor: Color = checkedThumbColor
        .copy(alpha = ContentAlpha.disabled)
        .compositeOver(MaterialTheme.colorScheme.surface),
    disabledCheckedTrackColor: Color = checkedTrackColor
        .copy(alpha = ContentAlpha.disabled)
        .compositeOver(MaterialTheme.colorScheme.surface),
    disabledUncheckedThumbColor: Color = uncheckedThumbColor
        .copy(alpha = ContentAlpha.disabled)
        .compositeOver(MaterialTheme.colorScheme.surface),
    disabledUncheckedTrackColor: Color = uncheckedTrackColor
        .copy(alpha = ContentAlpha.disabled)
        .compositeOver(MaterialTheme.colorScheme.surface)
) = colors(
    checkedThumbColor,
    checkedTrackColor,
    checkedTrackAlpha,
    uncheckedThumbColor,
    uncheckedTrackColor,
    uncheckedTrackAlpha,
    disabledCheckedThumbColor,
    disabledCheckedTrackColor,
    disabledUncheckedThumbColor,
    disabledUncheckedTrackColor
)

@Composable
fun SwitchMaterial3(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    colors: SwitchColors = SwitchDefaults.material3Colors()
) {
    androidx.compose.material.Switch(
        checked, onCheckedChange, modifier, enabled, interactionSource, colors
    )
}