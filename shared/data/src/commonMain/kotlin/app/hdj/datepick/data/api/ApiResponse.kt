package app.hdj.datepick.data.api

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse<T>(
    val message : String,
    val code : Int,
    val data : T?
)