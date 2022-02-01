package app.hdj.datepick.data.entity.place

import app.hdj.datepick.domain.model.place.Place
import kotlinx.serialization.Serializable

@Serializable
data class PlaceData(
    override val id: Long,
    override val kakaoId: Long,
    override val name: String,
    override val category: PlaceCategoryData,
    override val address: String,
    override val latitude: Double,
    override val longitude: Double,
    override val rating: Double,
    override val isPicked: Boolean,
    override val photo: String?,
) : Place

@Serializable
data class PlaceCategoryData(
    override val category: String,
    override val type: String,
    override val subtype: String
) : Place.Category