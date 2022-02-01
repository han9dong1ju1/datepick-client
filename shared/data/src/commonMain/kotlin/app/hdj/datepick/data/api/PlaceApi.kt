package app.hdj.datepick.data.api

import app.hdj.datepick.data.entity.place.PlaceData
import app.hdj.datepick.utils.di.Inject
import app.hdj.datepick.utils.di.Singleton
import io.ktor.client.*
import io.ktor.client.request.*

interface PlaceApi : Api {

    override val basePath: String get() = "api/v1/place"

    suspend fun getById(id: Long): ApiResponse<PlaceData>

    suspend fun search(query: String, sort: String): ApiResponse<List<PlaceData>>

}

@Singleton
class PlaceApiImp @Inject constructor(override val client: HttpClient) : PlaceApi {

    override suspend fun getById(id: Long) = get<ApiResponse<PlaceData>>("$id/")

    override suspend fun search(query: String, sort: String) =
        get<ApiResponse<List<PlaceData>>> {
            parameter("query", query)
            parameter("sort", sort)
        }

}