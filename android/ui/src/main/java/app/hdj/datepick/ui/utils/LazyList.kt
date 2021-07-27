package app.hdj.datepick.ui.utils

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemsIndexed
import app.hdj.shared.client.domain.StateData


@ExperimentalAnimationApi
@ExperimentalFoundationApi
fun <T> LazyListScope.statefulStickyHeader(
    key : String,
    state: StateData<T>,
    error: @Composable LazyItemScope.(Throwable?) -> Unit = {},
    loading: @Composable LazyItemScope.() -> Unit = {},
    itemContent: @Composable LazyItemScope.(T) -> Unit,
) {
    stickyHeader(key = key) {
        AnimatedContent(targetState = state) {
            when (state) {
                is StateData.Failed -> error(state.throwable)
                is StateData.Loading -> loading()
                is StateData.Success -> itemContent(state.data)
            }
        }
    }
}

fun <T> LazyListScope.statefulItem(
    state: StateData<T>,
    error: @Composable LazyItemScope.(Throwable?) -> Unit = {},
    loading: @Composable LazyItemScope.() -> Unit = {},
    itemContent: @Composable LazyItemScope.(T) -> Unit,
) {

    when (state) {
        is StateData.Failed -> {
            item {
                error(state.throwable)
            }
        }
        is StateData.Loading ->
            item {
                loading()
            }
        is StateData.Success -> {
            item {
                itemContent(state.data)
            }
        }
    }

}

fun <T> LazyListScope.statefulItems(
    state: StateData<List<T>>,
    error: @Composable LazyItemScope.(Throwable?) -> Unit = {},
    loading: @Composable LazyItemScope.() -> Unit = {},
    itemContent: @Composable LazyItemScope.(Int, T) -> Unit,
) {

    when (state) {
        is StateData.Failed -> {
            if (state.cachedData.isNullOrEmpty())
                item {
                    error(state.throwable)
                }
            else
                itemsIndexed(state.cachedData.orEmpty()) { i, data ->
                    itemContent(i, data)
                }
        }
        is StateData.Loading ->
            item {
                loading()
            }
        is StateData.Success -> {
            itemsIndexed(state.data) { i, data ->
                itemContent(i, data)
            }
        }
    }

}

fun <T : Any> LazyListScope.pagedItems(
    items: LazyPagingItems<T>,
    error: @Composable LazyItemScope.(error: Throwable?, retry: () -> Unit) -> Unit = { _, _ -> },
    loading: @Composable LazyItemScope.() -> Unit = {},
    itemContent: @Composable LazyItemScope.(Int, T) -> Unit,

    ) {

    itemsIndexed(items) { i, item ->
        if (item == null) loading()
        else itemContent(i, item)
    }

    items.apply {

        when {
            loadState.refresh is LoadState.Loading -> {
                item { loading() }
            }
            loadState.append is LoadState.Loading -> {
                item { loading() }
            }
            loadState.refresh is LoadState.Error -> {
                val e = loadState.refresh as LoadState.Error
                item {
                    error(e.error) { retry() }
                }
            }
            loadState.append is LoadState.Error -> {
                val e = loadState.refresh as LoadState.Error
                item {
                    error(e.error) { retry() }
                }
            }
        }
    }


}
