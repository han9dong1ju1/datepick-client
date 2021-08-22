package app.hdj.datepick.ui.utils

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp

@Composable
fun LazyListState.isFirstItemScrolled(limit: Dp): Boolean {
    return firstVisibleItemIndex != 0 ||
            firstVisibleItemIndex == 0 && firstVisibleItemScrollOffset >= with(LocalDensity.current) {
        limit.roundToPx()
    }

}