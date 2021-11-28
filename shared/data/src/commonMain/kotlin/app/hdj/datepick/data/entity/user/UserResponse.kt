package app.hdj.datepick.data.entity.user

import app.hdj.datepick.data.const.s3ImageUrl
import app.hdj.datepick.domain.model.user.User
import app.hdj.datepick.domain.model.user.UserGender
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    @SerialName("id") override val id: Long,
    @SerialName("nickname") override val nickname: String,
    @SerialName("profile_image") override var profileImage: String?,
    @SerialName("uid") override val uid: String,
    @SerialName("gender") override val gender: UserGender
) : User {

    init {
        val image = profileImage
        if (image != null && !image.startsWith("http")) {
            profileImage = s3ImageUrl(profileImage!!)
        }
    }

    override var isMe: Boolean = false
    fun asMe(): UserResponse = this.apply { isMe = true }

}