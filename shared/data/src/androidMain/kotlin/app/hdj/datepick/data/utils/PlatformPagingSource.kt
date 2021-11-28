package app.hdj.datepick.data.utils

import androidx.paging.PagingSource
import androidx.paging.PagingState
import app.hdj.datepick.data.api.ApiPagingData

actual class PlatformPagingSource<T : Any> actual constructor(private val call: suspend (Int) -> ApiPagingData<T>) :
    PagingSource<Int, T>() {

    override fun getRefreshKey(state: PagingState<Int, T>): Int? = null


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        val prevKey = params.key ?: 0
        return call.runCatching {
            invoke(prevKey)
        }.fold({
            LoadResult.Page(
                it.content,
                prevKey,
                if (it.isLastPage) null else it.currentPage + 1
            )
        }, {
            LoadResult.Error(it)
        })

    }


}