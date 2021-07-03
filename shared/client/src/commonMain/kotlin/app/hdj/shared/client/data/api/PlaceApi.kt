package app.hdj.shared.client.data.api

import app.hdj.shared.client.data.ApiResponse
import app.hdj.shared.client.domain.PlaceQuery
import app.hdj.shared.client.domain.entity.Place
import io.ktor.client.*
import io.ktor.client.request.*

open class PlaceApi(private val client: HttpClient) {

    suspend fun getPlace(placeId: String) = client.get<ApiResponse<Place>>(path = "/place/$placeId")

    suspend fun queryPlace(
        search: String?,
        sort: PlaceQuery.Sort,
        page: Int? = null,
    ) = client.get<ApiResponse<PagingResponse<Int, Place>>>(path = "/place/") {
        parameter("search", search)
        parameter("sort", sort.value)
        parameter("page", page)
    }

}