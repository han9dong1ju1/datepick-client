package app.hdj.datepick.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun InsetTopBar(
    title: @Composable () -> Unit = {},
    modifier: Modifier = Modifier,
    navigationIcon: @Composable (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {},
    backgroundColor: Color = MaterialTheme.colors.background,
    contentColor: Color = contentColorFor(backgroundColor),
    elevation: Dp = 0.dp,
    enableDivider: Boolean = true
) {
    Surface(
        modifier = modifier,
        color = backgroundColor
    ) {
        Box(Modifier.statusBarsPadding()) {
            TopAppBar(
                title,
                modifier = Modifier.fillMaxWidth().height(60.dp),
                navigationIcon,
                actions,
                Color.Transparent,
                contentColor,
                elevation
            )

            AnimatedVisibility(
                enableDivider,
                modifier = Modifier.align(Alignment.BottomCenter)
            ) {
                Divider(
                    color = MaterialTheme.colors.onBackground.copy(0.05f)
                )
            }
        }
    }
}

@Composable
fun InsetBottomBar(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colors.background,
    contentColor: Color = contentColorFor(backgroundColor),
    elevation: Dp = 0.dp,
    enableDivider: Boolean = true,
    actions: @Composable RowScope.() -> Unit = {},
) {
    Surface(
        modifier = modifier,
        color = backgroundColor
    ) {
        Box(Modifier.navigationBarsPadding()) {
            BottomAppBar(
                modifier = Modifier.fillMaxWidth().height(60.dp),
                Color.Transparent,
                contentColor,
                elevation = elevation,
                content = actions,
            )

            AnimatedVisibility(
                enableDivider,
                modifier = Modifier.align(Alignment.TopCenter)
            ) {
                Divider(
                    color = MaterialTheme.colors.onBackground.copy(0.05f)
                )
            }
        }
    }
}