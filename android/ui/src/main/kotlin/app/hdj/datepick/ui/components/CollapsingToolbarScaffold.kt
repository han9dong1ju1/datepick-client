package app.hdj.datepick.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.Velocity
import me.onebone.toolbar.*

@Composable
fun BasicCollapsingToolbarScaffold(
    modifier: Modifier = Modifier,
    state: CollapsingToolbarScaffoldState = rememberCollapsingToolbarScaffoldState(),
    scrollStrategy: ScrollStrategy = ScrollStrategy.ExitUntilCollapsed,
    enabled: Boolean = true,
    toolbarModifier: Modifier = Modifier,
    background: @Composable CollapsingToolbarScope.() -> Unit = {},
    foreground: @Composable CollapsingToolbarScope.() -> Unit = {},
    body: @Composable CollapsingToolbarScaffoldScope.() -> Unit
) {

    CollapsingToolbarScaffold(
        modifier = modifier.nestedScroll(object : NestedScrollConnection {
            override suspend fun onPostFling(
                consumed: Velocity,
                available: Velocity
            ): Velocity {
                @OptIn(ExperimentalToolbarApi::class)
                if (state.toolbarState.progress < 0.5) {
                    state.toolbarState.collapse()
                } else {
                    state.toolbarState.expand()
                }
                return Velocity.Zero
            }
        }),
        state = state,
        scrollStrategy = scrollStrategy,
        enabled = enabled,
        toolbarModifier = toolbarModifier,
        toolbar = {
            background()
            foreground()
        },
        body = body
    )

}