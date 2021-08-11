package app.hdj.datepick.ui.components

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.google.accompanist.insets.ui.TopAppBar

@Composable
fun TopAppBarBackButton(
    contentColor: Color = MaterialTheme.colors.onSurface
) {
    val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    IconButton(onClick = { backDispatcher?.onBackPressed() }) {
        Icon(imageVector = Icons.Rounded.ArrowBack, null, tint = contentColor)
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
    TopAppBar(
        title,
        modifier,
        contentPadding,
        navigationIcon,
        actions,
        backgroundColor,
        contentColor,
        elevation
    )
}