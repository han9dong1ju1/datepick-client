package app.hdj.datepick.ui.icon

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.runtime.Composable

@Composable
fun LikeIconButton(isLiked: Boolean, onLiked : () -> Unit) {

    val color = if (isLiked) MaterialTheme.colors.secondary else MaterialTheme.colors.onSurface
    val icon = if (isLiked) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder

    IconButton(onClick = onLiked) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = color
        )
    }
}