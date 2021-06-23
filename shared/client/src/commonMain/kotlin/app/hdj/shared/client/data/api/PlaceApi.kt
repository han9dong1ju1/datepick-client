package app.hdj.shared.client.data.api

import app.hdj.shared.client.data.ApiResponse
import app.hdj.shared.client.domain.entity.Place
import io.ktor.client.*
import io.ktor.client.request.*

class PlaceApi(private val client: HttpClient) {

    suspend fun getPlace(placeId: String) = client.get<ApiResponse<Place>>(path = "/place/$placeId")

}