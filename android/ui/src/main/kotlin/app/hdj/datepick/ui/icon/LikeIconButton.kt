package app.hdj.datepick.ui.icon

import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LikeIconButton(isLiked: Boolean, onLiked: () -> Unit) {
    IconButton(onClick = onLiked) {
        val defaultColor = LocalContentColor.current.copy(alpha = LocalContentAlpha.current)
        val color = if (isLiked) MaterialTheme.colors.secondary else defaultColor
        val icon = if (isLiked) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder
        Icon(
            modifier = Modifier.size(20.dp),
            imageVector = icon,
            contentDescription = null,
            tint = color
        )
    }
}