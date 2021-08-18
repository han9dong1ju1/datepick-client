package app.hdj.datepick.ui.utils

import androidx.compose.foundation.ScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp


@Composable
fun ScrollState.isFirstItemScrolled(limit: Dp): Boolean {
    return value >= with(LocalDensity.current) {
        limit.roundToPx()
    }
}