package app.hdj.datepick.ui.components

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun TopAppBarBackButton(
    icon: ImageVector = Icons.Rounded.ArrowBack,
    contentColor: Color = MaterialTheme.colors.onSurface
) {
    val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    IconButton(onClick = { backDispatcher?.onBackPressed() }) {
        Icon(imageVector = icon, null, tint = contentColor)
    }
}