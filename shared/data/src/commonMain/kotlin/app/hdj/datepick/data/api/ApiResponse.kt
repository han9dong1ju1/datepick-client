package app.hdj.datepick.data.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse<T>(
    @SerialName("message") val message : String?,
    @SerialName("code") val code : Int,
    @SerialName("data") val data : T?
)