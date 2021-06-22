package app.hdj.shared.client.data

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse<Data>(
    val code : Int,
    val message : String,
    val data : Data
)