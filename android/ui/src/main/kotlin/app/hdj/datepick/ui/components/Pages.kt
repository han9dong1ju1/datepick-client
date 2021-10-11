package app.hdj.datepick.ui.components

import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield

@ExperimentalPagerApi
@Composable
fun <T> ViewPager(
    modifier: Modifier = Modifier,
    list: List<T>,
    pagerState: PagerState = rememberPagerState(),
    itemSpacing: Dp = 0.dp,
    autoScrollDelay: Long = 0,
    content: @Composable PagerScope.(T, Int) -> Unit
) {
    val isAutoScrollEnabled = remember(autoScrollDelay) { autoScrollDelay != 0L }

    if (isAutoScrollEnabled) {
        LaunchedEffect(Unit) {
            while (true) {
                yield()
                delay(autoScrollDelay)

                tween<Float>(500)
                pagerState.animateScrollToPage(
                    page = (pagerState.currentPage + 1) % (pagerState.pageCount)
                )
            }
        }
    }

    HorizontalPager(
        modifier = modifier.fillMaxWidth(),
        state = pagerState,
        itemSpacing = itemSpacing,
        count = list.size
    ) {
        content(list[it], it)
    }
}