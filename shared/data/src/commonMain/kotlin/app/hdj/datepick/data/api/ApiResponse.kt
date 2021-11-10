package app.hdj.datepick.data.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse<T>(
    @SerialName("message") val message : String?,
    @SerialName("error") val error : String?,
    @SerialName("data") val data : T
)