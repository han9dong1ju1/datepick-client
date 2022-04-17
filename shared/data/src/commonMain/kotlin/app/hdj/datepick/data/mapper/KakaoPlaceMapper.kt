package app.hdj.datepick.data.mapper

import app.hdj.datepick.data.model.response.kakao_place.KakaoPlaceSearchResponse
import app.hdj.datepick.domain.model.place.KakaoPlaceSearch
import app.hdj.datepick.utils.location.LatLng

object KakaoPlaceMapper : Mapper<KakaoPlaceSearchResponse, KakaoPlaceSearch> {

    override fun KakaoPlaceSearchResponse.asDomain() = KakaoPlaceSearch(
        meta = KakaoPlaceSearch.Meta(
            sameName = KakaoPlaceSearch.Meta.SameName(
                region = meta.sameName.region,
                keyword = meta.sameName.keyword,
                selectedRegion = meta.sameName.selectedRegion,
            ),
            pageableCount = meta.pageableCount,
            totalCount = meta.totalCount,
            isEnd = meta.isEnd,
        ),
        documents.map {
            KakaoPlaceSearch.Document(
                placeName = it.placeName,
                distance = it.distance,
                placeUrl = it.placeUrl,
                categoryName = it.categoryName,
                addressName = it.addressName,
                roadAddressName = it.roadAddressName,
                id = it.id,
                phone = it.phone,
                categoryGroupCode = it.categoryGroupCode,
                categoryGroupName = it.categoryGroupName,
                latLng = LatLng(it.x.toDouble(), it.y.toDouble()),
            )
        }
    )


}