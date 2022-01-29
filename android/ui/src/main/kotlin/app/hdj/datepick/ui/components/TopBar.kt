package app.hdj.datepick.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.google.accompanist.insets.ui.TopAppBar

@Composable
fun BaseTopBar(
    title: @Composable () -> Unit = {},
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = rememberInsetsPaddingValues(LocalWindowInsets.current.statusBars),
    navigationIcon: @Composable (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {},
    backgroundColor: Color = MaterialTheme.colors.background,
    contentColor: Color = contentColorFor(backgroundColor),
    elevation: Dp = 0.dp,
    enableDivider: Boolean = true
) {
    Box {
        TopAppBar(
            title, modifier, contentPadding, navigationIcon, actions, backgroundColor, contentColor, elevation
        )
        if (enableDivider) {
            Divider(
                modifier = Modifier.align(Alignment.BottomCenter),
                color = MaterialTheme.colors.onBackground.copy(0.05f)
            )
        }
    }
}