package app.hdj.datepick.data.request.user

import kotlinx.serialization.Serializable

@Serializable
data class UserUnregisterRequest(
    val reason: String
)