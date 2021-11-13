package app.hdj.datepick.data.request

import kotlinx.serialization.Serializable

@Serializable
data class UserUnregisterRequest(
    val reason: String
)