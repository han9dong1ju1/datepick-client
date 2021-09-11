package app.hdj.datepick.data.api

import app.hdj.datepick.data.entity.PlaceResponse
import app.hdj.datepick.utils.Inject
import app.hdj.datepick.utils.Singleton
import io.ktor.client.*
import io.ktor.client.request.*

interface PlaceApi : Api {

    override val basePath: String get() = "api/v1/place"

    suspend fun getById(id: Long): ApiResponse<PlaceResponse>

    suspend fun search(query: String, sort: String): ApiResponse<List<PlaceResponse>>

}

@Singleton
class PlaceApiImp @Inject constructor(override val client: HttpClient) : PlaceApi {

    override suspend fun getById(id: Long) = get<ApiResponse<PlaceResponse>>("$id/")

    override suspend fun search(query: String, sort: String) =
        get<ApiResponse<List<PlaceResponse>>> {
            parameter("query", query)
            parameter("sort", sort)
        }

}