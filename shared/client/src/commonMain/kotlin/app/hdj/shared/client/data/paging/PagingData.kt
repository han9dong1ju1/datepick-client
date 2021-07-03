package app.hdj.shared.client.data.paging

sealed class PageData<K, T> {

    data class Success<K, T>(val nextKey: K? = null, val list : List<T> = emptyList()) : PageData<K, T>()
    data class Failed<K, T>(val throwable: Throwable) : PageData<K, T>()

}