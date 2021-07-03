package app.hdj.shared.client.data.paging

import app.hdj.shared.client.domain.StateData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


actual class PlatformPagingData<T : Any> constructor(
    val response: StateData<List<T>>,
    val onLoadMore: () -> Unit
)

actual open class PlatformPagingSource<Key : Any, Value : Any> actual constructor(
    private val pageData: suspend (Key?) -> PageData<Key, Value>
) {

    private var nextPageKey: Key? = null
    private val _list = MutableSharedFlow<StateData<List<Value>>>()
    val list = _list.asSharedFlow()

    fun loadMore() {
        CoroutineScope(Dispatchers.Main).launch {
            _list.emit(StateData.Loading())
            withContext(Dispatchers.Default) {
                when (val data = pageData(nextPageKey)) {
                    is PageData.Success -> {
                        nextPageKey = data.nextKey
                        _list.emit(StateData.Success(data.list))
                    }
                    is PageData.Failed -> {
                        _list.emit(StateData.Failed(throwable = data.throwable))
                    }
                }
            }
        }
    }

}

actual fun <Key : Any, Value : Any> PlatformPagingSource<Key, Value>.asPager(pageSize: Int): Flow<PlatformPagingData<Value>> {
    return this.list.map {
        PlatformPagingData(it, this::loadMore)
    }
}