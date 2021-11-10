package app.hdj.datepick.data.entity

import app.hdj.datepick.domain.model.user.User
import app.hdj.datepick.domain.model.user.UserGender
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    @SerialName("id") override val id: Long,
    @SerialName("nickname") override val nickname: String,
    @SerialName("profile_url") override val profileUrl: String?,
    @SerialName("uid") override val uid: String,
    @SerialName("gender") override val gender: UserGender
) : User {

    override var isMe: Boolean = false
    fun asMe(): UserResponse = this.apply { isMe = true }

}