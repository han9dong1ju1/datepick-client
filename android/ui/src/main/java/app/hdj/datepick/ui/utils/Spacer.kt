package app.hdj.datepick.ui.utils

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun VerticalMargin(dp : Dp = 20.dp) {
    Spacer(modifier = Modifier.height(dp))
}

@Composable
fun HorizontalMargin(dp : Dp = 20.dp) {
    Spacer(modifier = Modifier.width(dp))
}

fun LazyListScope.verticalMargin(dp : Dp = 20.dp) = item {
    VerticalMargin(dp)
}

fun LazyListScope.horizontalMargin(dp : Dp = 20.dp) = item {
    HorizontalMargin(dp)
}