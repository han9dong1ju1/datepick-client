package app.hdj.datepick.data.request

import kotlinx.serialization.Serializable

@Serializable
data class UserProfileRequest(
    val nickname: String?,
    val profileImageUrl: String?,
    val gender : String?
)