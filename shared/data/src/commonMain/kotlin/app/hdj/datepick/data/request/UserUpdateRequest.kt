package app.hdj.datepick.data.request

import kotlinx.serialization.Serializable

@Serializable
data class UserUpdateRequest(
    val nickname: String?,
    val profileImageUrl: String?
)