package app.hdj.datepick.data.entity.user

import app.hdj.datepick.data.const.s3ImageUrl
import app.hdj.datepick.domain.model.user.User
import app.hdj.datepick.domain.model.user.UserGender
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    @SerialName("id") val id: Long,
    @SerialName("nickname") val nickname: String,
    @SerialName("image_url") var imageUrl: String?,
    @SerialName("gender") val gender: UserGender?
) {

    init {
        val image = imageUrl
        if (image != null && !image.startsWith("http")) {
            imageUrl = s3ImageUrl(imageUrl!!)
        }
    }

}