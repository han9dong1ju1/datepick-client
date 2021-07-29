package app.hdj.shared.client.domain.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Place(
    @SerialName("id") override val id: String,
    @SerialName("address") val address: String,
    @SerialName("is_picked") val isPicked: Boolean,
    @SerialName("category") val category: PlaceCategory,
    @SerialName("latitude") val latitude: Double,
    @SerialName("longitude") val longitude: Double,
    @SerialName("name") val name: String,
    @SerialName("photo_url") val photoUrl: String?,
    @SerialName("rating") val rating: Float
) : Id