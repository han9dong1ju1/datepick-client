package app.hdj.datepick.android.ui.screens.main.home

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.hdj.datepick.android.ui.icons.DatePickIcons
import app.hdj.datepick.android.ui.icons.Logo
import app.hdj.datepick.ui.components.DatePickTopAppBar
import app.hdj.datepick.ui.styles.DatePickTheme

@Composable
fun HomeScreenTopBar(
    collapsed: Boolean
) {

    val background by animateColorAsState(
        targetValue = if (collapsed) MaterialTheme.colors.surface else MaterialTheme.colors.surface.copy(
            0.0f
        ),
        animationSpec = tween(450)
    )

    val tint by animateColorAsState(
        targetValue = if (collapsed) MaterialTheme.colors.onSurface else Color.White,
        animationSpec = tween(450)
    )

    DatePickTopAppBar(
        backgroundColor = background,
        elevation = 0.dp,
        title = {
            Image(
                imageVector = DatePickIcons.Logo, contentDescription = null,
                colorFilter = ColorFilter.tint(tint)
            )
        }
    )

}

@Composable
@Preview
fun HomeScreenTopBarPreview() {
    DatePickTheme {
        HomeScreenTopBar(true)
    }
}