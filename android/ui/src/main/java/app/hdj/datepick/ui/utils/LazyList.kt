package app.hdj.datepick.ui.utils

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import androidx.paging.compose.itemsIndexed
import app.hdj.shared.client.domain.StateData

fun <T> LazyListScope.statefulItems(
    state: StateData<List<T>>,
    error: @Composable (Throwable?) -> Unit = {},
    loading: @Composable () -> Unit = {},
    itemContent: @Composable (Int, T) -> Unit,
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
    error: @Composable (error : Throwable?, retry : () -> Unit) -> Unit = { _, _ -> },
    loading: @Composable () -> Unit = {},
    itemContent: @Composable (Int, T) -> Unit,

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
