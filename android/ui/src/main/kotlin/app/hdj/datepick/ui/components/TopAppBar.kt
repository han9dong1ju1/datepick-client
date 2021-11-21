package app.hdj.datepick.ui.components

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.google.accompanist.insets.statusBarsHeight

@Composable
fun TopAppBarBackButton(
    icon: ImageVector = Icons.Rounded.ArrowBack,
    contentColor: Color = MaterialTheme.colorScheme.onSurface
) {
    val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    IconButton(onClick = { backDispatcher?.onBackPressed() }) {
        Icon(imageVector = icon, null, tint = contentColor)
    }
}

@Composable
fun InsetLargeTopAppBar(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    colors: TopAppBarColors = TopAppBarDefaults.largeTopAppBarColors(),
    scrollBehavior: TopAppBarScrollBehavior? = null
) {

    Column {
        val color by colors.containerColor(scrollFraction = scrollBehavior?.scrollFraction ?: 0f)

        Surface(
            modifier = Modifier
                .statusBarsHeight()
                .fillMaxWidth(),
            color = color
        ) {}

        LargeTopAppBar(
            title,
            modifier,
            navigationIcon,
            actions,
            colors,
            scrollBehavior
        )
    }

}

@Composable
fun InsetSmallTopAppBar(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    colors: TopAppBarColors = TopAppBarDefaults.largeTopAppBarColors(),
    scrollBehavior: TopAppBarScrollBehavior? = null
) {

    Column {
        val color by colors.containerColor(scrollFraction = scrollBehavior?.scrollFraction ?: 0f)

        Surface(
            modifier = Modifier
                .statusBarsHeight()
                .fillMaxWidth(),
            color = color
        ) {}

        SmallTopAppBar(
            title,
            modifier,
            navigationIcon,
            actions,
            colors,
            scrollBehavior
        )
    }

}
@Composable
fun InsetMediumTopAppBar(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    colors: TopAppBarColors = TopAppBarDefaults.largeTopAppBarColors(),
    scrollBehavior: TopAppBarScrollBehavior? = null
) {

    Column {
        val color by colors.containerColor(scrollFraction = scrollBehavior?.scrollFraction ?: 0f)

        Surface(
            modifier = Modifier
                .statusBarsHeight()
                .fillMaxWidth(),
            color = color
        ) {}

        MediumTopAppBar(
            title,
            modifier,
            navigationIcon,
            actions,
            colors,
            scrollBehavior
        )
    }

}