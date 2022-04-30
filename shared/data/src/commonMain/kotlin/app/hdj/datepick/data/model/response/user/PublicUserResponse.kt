package app.hdj.datepick.data.model.response.user

import app.hdj.datepick.data.const.s3ImageUrl
import app.hdj.datepick.domain.model.user.UserGender
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PublicUserResponse(
    @SerialName("id") val id: Long,
    @SerialName("nickname") val nickname : String,
    @SerialName("gender") val gender: UserGender?,
    @SerialName("image_url") var imageUrl: String?,
) {

    init {
        val image = imageUrl
        if (image != null && !image.startsWith("http")) {
            imageUrl = s3ImageUrl(imageUrl!!)
        }
    }

}