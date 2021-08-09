package app.hdj.datepick.data.request

import kotlinx.serialization.Serializable

@Serializable
data class UserUnregisterRequest(
    val type: Int,
    val reason: String?
)