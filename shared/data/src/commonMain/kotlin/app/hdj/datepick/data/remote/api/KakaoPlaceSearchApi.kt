package app.hdj.datepick.data.remote.api

import app.hdj.datepick.data.model.response.kakao_place.KakaoPlaceSearchResponse
import app.hdj.datepick.data.remote.Api
import app.hdj.datepick.data.remote.get
import app.hdj.datepick.domain.usecase.place.params.KakaoPlaceSearchQueryParams
import app.hdj.datepick.utils.di.Inject
import app.hdj.datepick.utils.di.Singleton
import io.ktor.client.*
import io.ktor.client.request.*

interface KakaoPlaceSearchApi : Api {

    override val basePath: String get() = "/v2/local/search/keyword.json"

    suspend fun search(
        params: KakaoPlaceSearchQueryParams
    ): KakaoPlaceSearchResponse

}

@Singleton
class KakaoPlaceSearchApiImp @Inject constructor(override val client: HttpClient) :
    KakaoPlaceSearchApi {

    override suspend fun search(params: KakaoPlaceSearchQueryParams): KakaoPlaceSearchResponse = get {
        with(params) {
            parameter("query", query)
            latLng?.let {
                parameter("sort", "distance")
                parameter("x", it.longitude)
                parameter("y", it.latitude)
            }
        }
    }

}