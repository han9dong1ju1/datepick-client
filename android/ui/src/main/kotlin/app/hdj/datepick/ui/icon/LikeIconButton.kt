package app.hdj.datepick.ui.icon

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.runtime.Composable

@Composable
fun LikeIconButton(isLiked: Boolean, onLiked: () -> Unit) {
    IconButton(onClick = onLiked) {
        val defaultColor = LocalContentColor.current.copy(alpha = LocalContentAlpha.current)
        val color = if (isLiked) MaterialTheme.colors.secondary else defaultColor
        val icon = if (isLiked) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = color
        )
    }
}