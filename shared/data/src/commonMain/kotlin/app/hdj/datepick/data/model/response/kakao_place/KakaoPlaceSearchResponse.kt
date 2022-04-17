package app.hdj.datepick.data.model.response.kakao_place

import kotlinx.serialization.SerialName

/*
{
  "meta": {
    "same_name": {
      "region": [],
      "keyword": "카카오프렌즈",
      "selected_region": ""
    },
    "pageable_count": 14,
    "total_count": 14,
    "is_end": true
  },
  "documents": [
    {
      "place_name": "카카오프렌즈 코엑스점",
      "distance": "418",
      "place_url": "http://place.map.kakao.com/26338954",
      "category_name": "가정,생활 > 문구,사무용품 > 디자인문구 > 카카오프렌즈",
      "address_name": "서울 강남구 삼성동 159",
      "road_address_name": "서울 강남구 영동대로 513",
      "id": "26338954",
      "phone": "02-6002-1880",
      "category_group_code": "",
      "category_group_name": "",
      "x": "127.05902969025047",
      "y": "37.51207412593136"
    },
    ...
  ]
}
* */



@kotlinx.serialization.Serializable
data class KakaoPlaceSearchResponse(
    @SerialName("meta") val meta: Meta,
    @SerialName("documents") val documents: List<Document>
) {

    @kotlinx.serialization.Serializable
    data class Meta(
        @SerialName("same_name") val sameName: SameName,
        @SerialName("pageable_count") val pageableCount: Int,
        @SerialName("total_count") val totalCount: Int,
        @SerialName("is_end") val isEnd: Boolean
    ) {
        @kotlinx.serialization.Serializable
        data class SameName(
            @SerialName("region") val region: List<String>,
            @SerialName("keyword") val keyword: String,
            @SerialName("selected_region") val selectedRegion: String
        )
    }

    @kotlinx.serialization.Serializable
    data class Document(
        @SerialName("place_name") val placeName: String,
        @SerialName("distance") val distance: String,
        @SerialName("place_url") val placeUrl: String,
        @SerialName("category_name") val categoryName: String,
        @SerialName("address_name") val addressName: String,
        @SerialName("road_address_name") val roadAddressName: String,
        @SerialName("id") val id: String,
        @SerialName("phone") val phone: String,
        @SerialName("category_group_code") val categoryGroupCode: String,
        @SerialName("category_group_name") val categoryGroupName: String,
        @SerialName("x") val x: String,
        @SerialName("y") val y: String
    )

}

