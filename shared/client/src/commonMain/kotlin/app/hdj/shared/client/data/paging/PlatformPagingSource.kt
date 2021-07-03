package app.hdj.shared.client.data.paging

import kotlinx.coroutines.flow.Flow

expect open class PlatformPagingSource<Key : Any, Value : Any> constructor(
    pageData: suspend (Key?) -> PageData<Key, Value>
)

fun <Key : Any, Value : Any> createPagingSource(pageData: suspend (Key?) -> PageData<Key, Value>) =
    object : PlatformPagingSource<Key, Value>(pageData) {

    }

expect fun <Key : Any, Value : Any> PlatformPagingSource<Key, Value>.asPager(pageSize : Int): Flow<PlatformPagingData<Value>>