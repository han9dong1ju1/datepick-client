package app.hdj.datepick.data.model.request.place

import kotlinx.serialization.SerialName

/*
* {
  *"kakao_id": "17264478",
  *"name": "유시어터",
  *"address": "서울 강남구 도산대로58길 16",
  *"latitude": 37.5222236315,
  *"longitude": 127.0411822579,
	*"categories": "공연장,연극극장 > ~"
}
* */
@kotlinx.serialization.Serializable
data class PlaceAddRequest(
    @SerialName("kakao_id") val kakaoId: String,
    @SerialName("name") val name: String,
    @SerialName("address") val address: String,
    @SerialName("latitude") val latitude: Double,
    @SerialName("longitude") val longitude: Double,
    @SerialName("categories") val categories: String
)