package app.hdj.datepick.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.*
import kotlinx.coroutines.delay

@ExperimentalPagerApi
@Composable
fun <T> DatePickPager(
    modifier: Modifier = Modifier,
    list: List<T>,
    pagerState: PagerState = rememberPagerState(pageCount = list.size),
    itemSpacing: Dp = 0.dp,
    autoScrollEnabled: Boolean = false,
    content: @Composable PagerScope.(T, Int) -> Unit
) {

    LaunchedEffect(key1 = pagerState.isScrollInProgress) {
        if (!pagerState.isScrollInProgress) {
            while (autoScrollEnabled) {
                delay(5000)
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