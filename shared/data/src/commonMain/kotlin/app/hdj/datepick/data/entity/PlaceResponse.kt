package app.hdj.datepick.data.entity

import app.hdj.datepick.domain.model.place.Place
import kotlinx.serialization.Serializable

@Serializable
data class PlaceResponse(
    override val id : Long,
    override val kakaoId: Long,
    override val name : String,
    override val address: String,
    override val latitude: Double,
    override val longitude: Double,
    override val rating: Double,
    override val isPicked: Boolean,
    override val photos: List<String>,
) : Place