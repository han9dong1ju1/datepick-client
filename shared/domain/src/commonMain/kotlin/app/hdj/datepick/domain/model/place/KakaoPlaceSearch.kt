package app.hdj.datepick.domain.model.place

import app.hdj.datepick.utils.location.LatLng


data class KakaoPlaceSearch(
    val meta: Meta,
    val documents: List<Document>
) {

    data class Meta(
        val sameName: SameName,
        val pageableCount: Int,
        val totalCount: Int,
        val isEnd: Boolean
    ) {
        data class SameName(
            val region: List<String>,
            val keyword: String,
            val selectedRegion: String
        )
    }

    data class Document(
        val placeName: String,
        val distance: String,
        val placeUrl: String,
        val categoryName: String,
        val addressName: String,
        val roadAddressName: String,
        val id: String,
        val phone: String,
        val categoryGroupCode: String,
        val categoryGroupName: String,
        val latLng: LatLng
    )

}