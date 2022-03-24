package app.hdj.datepick.data.entity.place

import kotlinx.serialization.Serializable

@Serializable
data class PlaceResponse(
    val id: Long,
    val kakaoId: Long,
    val name: String,
    val category: PlaceCategoryResponse,
    val address: String,
    val latitude: Double,
    val longitude: Double,
    val rating: Double,
    val isPicked: Boolean,
    val photo: String?,
)

@Serializable
data class PlaceCategoryResponse(
    val category: String,
    val type: String,
    val subtype: String
)