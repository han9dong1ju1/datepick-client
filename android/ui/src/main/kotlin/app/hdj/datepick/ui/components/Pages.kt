@file:OptIn(ExperimentalComposeUiApi::class)

package app.hdj.datepick.ui.components

import android.view.MotionEvent
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import app.hdj.datepick.utils.PlatformLogger
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
    contentPadding: PaddingValues = PaddingValues(),
    autoScrollDelay: Long = 0,
    content: @Composable PagerScope.(T, Int) -> Unit
) {
    val isAutoScrollEnabled = remember(autoScrollDelay) { autoScrollDelay != 0L }

    if (isAutoScrollEnabled && pagerState.pageCount != 0) {
        LaunchedEffect(Unit) {
            while (true) {
                yield()
                delay(autoScrollDelay)
                pagerState.animateScrollToPage(
                    page = (pagerState.currentPage + 1) % (pagerState.pageCount)
                )
            }
        }
    }

    HorizontalPager(
        modifier = modifier
            .fillMaxWidth(),
        state = pagerState,
        itemSpacing = itemSpacing,
        contentPadding = contentPadding,
        count = list.size
    ) {
        content(list[it], it)
    }
}