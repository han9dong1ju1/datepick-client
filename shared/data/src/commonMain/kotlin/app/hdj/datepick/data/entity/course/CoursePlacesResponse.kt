package app.hdj.datepick.data.entity.course

import app.hdj.datepick.data.entity.place.PlaceResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoursePlacesResponse(
    @SerialName("memo") val memo: String,
    @SerialName("place_meta") val placeMeta: PlaceResponse,
    @SerialName("place_order") val placeOrder: Int,
    @SerialName("visit_time") val visitTime: String
)