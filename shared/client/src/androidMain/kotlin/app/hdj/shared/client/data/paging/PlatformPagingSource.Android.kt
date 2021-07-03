package app.hdj.shared.client.data.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.flow.Flow


actual open class PlatformPagingSource<Key : Any, Value : Any> actual constructor(
    private val pageData: suspend (Key?) -> PageData<Key, Value>
) : PagingSource<Key, Value>() {

    override fun getRefreshKey(state: PagingState<Key, Value>): Key? = null

    override suspend fun load(params: LoadParams<Key>): LoadResult<Key, Value> {
        return when (val result = pageData(params.key)) {
            is PageData.Success -> LoadResult.Page(
                data = result.list,
                nextKey = result.nextKey,
                prevKey = null
            )
            is PageData.Failed -> LoadResult.Error(result.throwable)
        }
    }

}


actual fun <Key : Any, Value : Any> PlatformPagingSource<Key, Value>.asPager(pageSize: Int): Flow<PlatformPagingData<Value>> {
    return Pager(PagingConfig(pageSize), null, { this }).flow
}