package app.hdj.datepick.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun BaseChip(
    modifier: Modifier = Modifier,
    text: String,
    textStyle: TextStyle = MaterialTheme.typography.body2,
    isSelected: Boolean = false,
    onClick: () -> Unit = {}
) {
    val color by animateColorAsState(
        targetValue = if (isSelected) MaterialTheme.colors.secondary
        else MaterialTheme.colors.surface
    )

    val textColor by animateColorAsState(
        targetValue = if (isSelected) MaterialTheme.colors.onSecondary
        else MaterialTheme.colors.onSurface
    )

    val cornerRadius by animateDpAsState(targetValue = if (isSelected) 20.dp else 10.dp)

    Surface(
        modifier = modifier,
        onClick = onClick,
        color = color,
        shape = RoundedCornerShape(cornerRadius)
    ) {
        Box {
            Text(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(horizontal = 14.dp, vertical = 7.dp),
                text = text,
                color = textColor,
                style = textStyle
            )
        }
    }
}