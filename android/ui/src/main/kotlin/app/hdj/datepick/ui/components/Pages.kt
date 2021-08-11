package app.hdj.datepick.ui.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import app.hdj.datepick.ui.utils.VerticalMargin
import com.google.accompanist.pager.*
import kotlinx.coroutines.delay

@ExperimentalPagerApi
@Composable
fun <T> DatePickPager(
    modifier: Modifier = Modifier,
    list: List<T>,
    pagerState : PagerState = rememberPagerState(pageCount = list.size),
    itemSpacing: Dp = 0.dp,
    autoScrollEnabled: Boolean = false,
    content: @Composable PagerScope.(T, Int) -> Unit
) {

    LaunchedEffect(key1 = autoScrollEnabled) {
        while (autoScrollEnabled) {
            delay(5000)
            if (!pagerState.isScrollInProgress) {
                if (pagerState.currentPage == pagerState.pageCount - 1) {
                    pagerState.animateScrollToPage(0)
                } else {
                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                }
            }
        }
    }

    HorizontalPager(
        modifier = modifier.fillMaxWidth(),
        state = pagerState,
        itemSpacing = itemSpacing
    ) {
        content(list[it], it)
    }
}