package app.hdj.datepick.domain.model.district

import app.hdj.datepick.utils.location.LatLng

data class District(
    val id: Long,
    val name: String,
//    val imageUrl : String,
    val latLng: LatLng
)