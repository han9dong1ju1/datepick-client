package app.hdj.datepick.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Chip(
    modifier: Modifier = Modifier,
    text: String,
    isSelected: Boolean = false,
    onClick: () -> Unit = {}
) {
    Surface(
        modifier = modifier,
        onClick = onClick,
        color = if (isSelected) MaterialTheme.colorScheme.secondary
        else MaterialTheme.colorScheme.surfaceVariant,
        shape = RoundedCornerShape(100.dp)
    ) {
        Text(
            modifier = Modifier.padding(
                horizontal = 14.dp,
                vertical = 6.dp
            ),
            text = text
        )
    }
}