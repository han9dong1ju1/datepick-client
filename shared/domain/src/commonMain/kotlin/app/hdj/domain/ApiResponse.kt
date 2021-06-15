package app.hdj.domain

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse<T>(
    val code : Int,
    val message : String,
    val data : T
)