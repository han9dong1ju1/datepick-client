package app.hdj.datepick.data.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PagingData<T>(
    @SerialName("total_count") val count : Long,
    @SerialName("current_page") val currentPage : Long,
    @SerialName("last") val isLastPage : Boolean,
    @SerialName("content") val content : List<T>,
)