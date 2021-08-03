package app.hdj.datepick.data.entity

import app.hdj.datepick.domain.model.user.User
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    override val id : String,
    override val nickname : String,
    override val profileImageUrl : String?
) : User