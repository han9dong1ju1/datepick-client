package app.hdj.shared.client.data.api

import kotlinx.serialization.Serializable

@Serializable
data class PagingResponse<K, D>(val key: K, val data: List<D>)