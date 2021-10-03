package app.hdj.datepick.ui.components

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.google.accompanist.insets.statusBarsHeight
import com.google.accompanist.insets.ui.TopAppBar
import me.onebone.toolbar.CollapsingToolbarScope

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

@Composable
fun BaseTopAppBar(
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit = {},
    contentPadding: PaddingValues = rememberInsetsPaddingValues(
        insets = LocalWindowInsets.current.statusBars,
        applyStart = true,
        applyTop = true,
        applyEnd = true,
    ),
    navigationIcon: @Composable (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {},
    backgroundColor: Color = MaterialTheme.colors.surface,
    contentColor: Color = contentColorFor(backgroundColor),
    elevation: Dp = 0.dp,
) {
    TopAppBar(
        {
            ProvideTextStyle(value = MaterialTheme.typography.h5) {
                CompositionLocalProvider(
                    LocalContentAlpha provides ContentAlpha.high,
                    content = title
                )
            }
        },
        modifier,
        contentPadding,
        navigationIcon,
        actions,
        backgroundColor,
        contentColor,
        elevation
    )
}

@Composable
fun CollapsingToolbarScope.BaseCollapsingTopBar(
    modifier: Modifier = Modifier,
    background: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit = {},
    titleWhenCollapsed: Alignment,
    titleWhenExpanded: Alignment,
    contentPadding: PaddingValues = rememberInsetsPaddingValues(
        insets = LocalWindowInsets.current.statusBars,
        applyStart = true,
        applyTop = true,
        applyEnd = true,
    ),
    navigationIcon: @Composable (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {},
    backgroundColor: Color = MaterialTheme.colors.surface,
    contentColor: Color = contentColorFor(backgroundColor),
    elevation: Dp = 0.dp,
) {
    var progress by remember { mutableStateOf(0f) }

    background()

    Row(
        modifier = Modifier
            .statusBarsHeight(AppBarHeight)
            .road(titleWhenCollapsed, titleWhenExpanded),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        ProvideTextStyle(value = MaterialTheme.typography.h5) {
            CompositionLocalProvider(
                LocalContentAlpha provides ContentAlpha.high,
                content = {

                    val startMargin = (72 + (20 - 72) * progress).dp

                    content(
                        PaddingValues(
                            top = contentPadding.calculateTopPadding(),
                            start = (if (navigationIcon != null) startMargin else 16.dp) - AppBarHorizontalPadding,
                            end = AppBarHorizontalPadding
                        )
                    )
                }
            )
        }
    }

    BaseTopAppBar(
        modifier
            .progress { progress = it },
        {},
        contentPadding,
        navigationIcon,
        actions,
        backgroundColor,
        contentColor,
        elevation
    )


}

private val AppBarHeight = 56.dp
private val AppBarHorizontalPadding = 4.dp
private val TitleInsetWithoutIcon = Modifier.width(16.dp - AppBarHorizontalPadding)
private val TitleIconModifier = Modifier
    .fillMaxHeight()
    .width(72.dp - AppBarHorizontalPadding)