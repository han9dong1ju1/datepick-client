package app.hdj.datepick.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Chip(
    modifier: Modifier = Modifier,
    text: String,
    isSelected: Boolean = false,
    onClick: () -> Unit = {}
) {
    val color by animateColorAsState(
        targetValue = if (isSelected) MaterialTheme.colorScheme.secondary
        else MaterialTheme.colorScheme.surfaceVariant
    )

    val textColor by animateColorAsState(
        targetValue = if (isSelected) MaterialTheme.colorScheme.onSecondary
        else MaterialTheme.colorScheme.onSurfaceVariant
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
                color = textColor
            )
        }
    }
}