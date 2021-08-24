package app.hdj.datepick.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield
import timber.log.Timber

@ExperimentalPagerApi
@Composable
fun <T> DatePickPager(
    modifier: Modifier = Modifier,
    list: List<T>,
    pagerState: PagerState = rememberPagerState(pageCount = list.size),
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

                pagerState.animateScrollToPage(
                    page = (pagerState.currentPage + 1) % (pagerState.pageCount),
                    animationSpec = tween(500)
                )
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