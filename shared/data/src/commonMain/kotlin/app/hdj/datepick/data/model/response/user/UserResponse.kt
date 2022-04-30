package app.hdj.datepick.data.model.response.user

import app.hdj.datepick.data.const.s3ImageUrl
import app.hdj.datepick.domain.model.user.UserGender
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/*
*
* 		"id": 2,
		"created_at": "2022-02-02T09:27:46.078043",
		"updated_at": "2022-02-02T11:23:53.805049",
		"uid": "v6B3HDX6o0eVyNAIei3gvrZo8i63",
		"provider": "GOOGLE",
		"nickname": "as",
		"gender": "F",
		"is_banned": false,
		"is_deleted": false,
		"image_url": null
* */

@Serializable
data class UserResponse(
    @SerialName("id") val id: Long,
    @SerialName("created_at") val createdAt: String,
    @SerialName("updated_at") val updatedAt: String,
    @SerialName("uid") val uid: String,
    @SerialName("provider") val provider: String,
    @SerialName("nickname") val nickname: String,
    @SerialName("email") val email: String,
    @SerialName("gender") val gender: String?,
    @SerialName("is_banned") val isBanned: Boolean,
    @SerialName("is_deleted") val isDeleted: Boolean,
    @SerialName("image_url") var imageUrl: String?,
) {

    init {
        val image = imageUrl
        if (image != null && !image.startsWith("http")) {
            imageUrl = s3ImageUrl(imageUrl!!)
        }
    }

}