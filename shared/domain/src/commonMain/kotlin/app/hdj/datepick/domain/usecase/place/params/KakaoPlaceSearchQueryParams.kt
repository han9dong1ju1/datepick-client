package app.hdj.datepick.domain.usecase.place.params

import app.hdj.datepick.utils.location.LatLng

data class KakaoPlaceSearchQueryParams(
    val query: String,
    val latLng: LatLng? = null
)
