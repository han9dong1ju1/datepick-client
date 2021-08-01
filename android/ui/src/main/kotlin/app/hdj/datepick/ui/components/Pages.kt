package app.hdj.datepick.ui.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import app.hdj.datepick.ui.utils.VerticalMargin
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay

@ExperimentalPagerApi
@Composable
fun <T> DatePickPager(
    modifier: Modifier = Modifier,
    list: List<T>,
    itemSpacing: Dp = 0.dp,
    autoScrollEnabled: Boolean = false,
    indicatorEnabled: Boolean = false,
    loading: @Composable () -> Unit = {},
    error: @Composable () -> Unit = {},
    content: @Composable (T) -> Unit
) {

    val pagerState = rememberPagerState(
        pageCount = list.size,
        infiniteLoop = true
    )

    LaunchedEffect(key1 = autoScrollEnabled) {
        if (autoScrollEnabled) {
            while (true) {
                if (!pagerState.isScrollInProgress) {
                    if (pagerState.currentPage == pagerState.pageCount - 1) {
                        pagerState.animateScrollToPage(0)
                    } else pagerState.animateScrollToPage(pagerState.currentPage + 1)
                }
                delay(5000)
            }
        }
    }

    Column(modifier = modifier) {
        HorizontalPager(
            modifier = Modifier.fillMaxWidth(),
            state = pagerState,
            itemSpacing = itemSpacing
        ) {
            content(list[it])
        }

        if (indicatorEnabled) {

            VerticalMargin(20.dp)

            HorizontalPagerIndicator(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                indicatorHeight = 6.dp,
                indicatorWidth = 6.dp,
                activeColor = MaterialTheme.colors.secondary,
                inactiveColor = MaterialTheme.colors.onBackground.copy(0.1f),
                spacing = 6.dp,
                pagerState = pagerState
            )

        }

    }
}