package app.hdj.datepick.data.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ApiPagingData<T>(
    @SerialName("total_count") val totalCount : Long,
    @SerialName("current_page") val currentPage : Int,
    @SerialName("last") val isLastPage : Boolean,
    @SerialName("content") val content : List<T>
)