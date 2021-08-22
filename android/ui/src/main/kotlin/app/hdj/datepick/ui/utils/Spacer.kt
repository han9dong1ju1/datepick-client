package app.hdj.datepick.ui.utils

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun LazyListScope.verticalMargin(dp : Dp = 20.dp) = item {
    Spacer(modifier = Modifier.height(dp))
}

fun LazyListScope.horizontalMargin(dp : Dp = 20.dp) = item {
    Spacer(modifier = Modifier.width(dp))
}