package app.hdj.datepick.android.ui.screens.main.home

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.hdj.datepick.android.ui.icons.DatePickIcons
import app.hdj.datepick.android.ui.icons.Logo
import app.hdj.datepick.ui.components.DatePickTopAppBar
import app.hdj.datepick.ui.styles.DatePickTheme

@Composable
fun HomeScreenTopBar(
    collapsed: Boolean,
    onSearchClicked: () -> Unit
) {

    val background by animateColorAsState(
        targetValue = if (collapsed) MaterialTheme.colors.surface else MaterialTheme.colors.surface.copy(
            0.0f
        ),
        animationSpec = tween(450)
    )

    val tintLogo by animateColorAsState(
        targetValue = if (collapsed) MaterialTheme.colors.secondary else Color.White,
        animationSpec = tween(450)
    )

    val tintIcon by animateColorAsState(
        targetValue = if (collapsed) MaterialTheme.colors.onSurface else Color.White,
        animationSpec = tween(450)
    )

    val elevation by animateDpAsState(
        targetValue = if (collapsed) 4.dp else 0.dp,
        animationSpec = tween(450)
    )

    DatePickTopAppBar(
        backgroundColor = background,
        elevation = elevation,
        actions = {
            IconButton(onClick = onSearchClicked) {
                Icon(imageVector = Icons.Rounded.Search, contentDescription = null, tint = tintIcon)
            }
        },
        title = {
            Icon(
                modifier = Modifier.width(80.dp),
                imageVector = DatePickIcons.Logo, contentDescription = null,
                tint = tintLogo
            )
        }
    )

}

@Composable
@Preview
fun HomeScreenTopBarPreview() {
    DatePickTheme {
        HomeScreenTopBar(true) {}
    }
}