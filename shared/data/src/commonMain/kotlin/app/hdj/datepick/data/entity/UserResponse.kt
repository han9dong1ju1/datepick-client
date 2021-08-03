package app.hdj.datepick.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val id : String,
    val firebaseId : String,
    val nickname : String,
    val profileImageUrl : String?
)