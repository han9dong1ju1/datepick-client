package app.hdj.datepick.ui.components

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.google.accompanist.insets.ui.TopAppBar

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
fun DatePickTopAppBar(
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

    Surface(
        color = backgroundColor,
        elevation = elevation,
        contentColor = contentColor,
        shape = RectangleShape,
        modifier = modifier
    ) {
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.high) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(contentPadding)
                    .height(AppBarHeight),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                content = {
                    if (navigationIcon == null) {
                        Spacer(TitleInsetWithoutIcon)
                    } else {
                        Row(TitleIconModifier, verticalAlignment = Alignment.CenterVertically) {
                            CompositionLocalProvider(
                                LocalContentAlpha provides ContentAlpha.high,
                                content = navigationIcon
                            )
                        }
                    }

                    Row(
                        Modifier
                            .fillMaxHeight()
                            .weight(1f),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        ProvideTextStyle(value = MaterialTheme.typography.h5) {
                            CompositionLocalProvider(
                                LocalContentAlpha provides ContentAlpha.high,
                                content = title
                            )
                        }
                    }

                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.high) {
                        Row(
                            Modifier.fillMaxHeight(),
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically,
                            content = actions
                        )
                    }
                }
            )
        }

    }

}

private val AppBarHeight = 56.dp
private val AppBarHorizontalPadding = 4.dp

private val TitleInsetWithoutIcon = Modifier.width(16.dp - AppBarHorizontalPadding)

private val TitleIconModifier = Modifier
    .fillMaxHeight()
    .width(56.dp - AppBarHorizontalPadding)