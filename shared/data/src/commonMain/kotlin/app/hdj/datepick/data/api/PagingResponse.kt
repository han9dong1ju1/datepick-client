package app.hdj.datepick.data.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PagingResponse<T>(
    @SerialName("total_count") val count: Long,
    @SerialName("current_page") val currentPage: Long,
    @SerialName("last") val isLastPage: Boolean,
    @SerialName("content") val content: List<T>,
)

fun <T> PagingResponse<T>.isEmpty() = content.isEmpty()

// create mapper function
fun <T, R> PagingResponse<T>.map(mapper: (T) -> R) = PagingResponse(count, currentPage, isLastPage, content.map(mapper))