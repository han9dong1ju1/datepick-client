package app.hdj.datepick.data.model.response.place

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlaceResponse(
    @SerialName("id") val id: Long,
    @SerialName("kakao_id") val kakaoId: String?,
    @SerialName("name") val name: String,
    @SerialName("categories") val categories: List<PlaceCategoryResponse>,
    @SerialName("address") val address: String,
    @SerialName("latitude") val latitude: Double,
    @SerialName("longitude") val longitude: Double,
    @SerialName("rating") val rating: Double?,
    @SerialName("is_picked") val isPicked: Boolean,
    @SerialName("image_url") val imageUrl: String? = null
)

@Serializable
data class PlaceCategoryResponse(
    @SerialName("id") val id: Long,
    @SerialName("name") val name: String
)