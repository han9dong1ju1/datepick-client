package app.hdj.datepick.data.remote.api

import app.hdj.datepick.data.model.response.place.PlaceResponse
import app.hdj.datepick.data.remote.Api
import app.hdj.datepick.data.remote.ApiResponse
import app.hdj.datepick.data.remote.PagingResponse
import app.hdj.datepick.data.remote.get
import app.hdj.datepick.domain.usecase.place.params.PlaceQueryParams
import app.hdj.datepick.utils.di.Inject
import app.hdj.datepick.utils.di.Singleton
import io.ktor.client.*
import io.ktor.client.request.*

interface PlaceApi : Api {

    override val basePath: String get() = "/v1/places"

    suspend fun getById(id: Long): ApiResponse<PlaceResponse>

    suspend fun queryPlaces(page: Long, placeQueryParams: PlaceQueryParams): ApiResponse<PagingResponse<PlaceResponse>>

}

@Singleton
class PlaceApiImp @Inject constructor(override val client: HttpClient) : PlaceApi {

    override suspend fun getById(id: Long) = get<ApiResponse<PlaceResponse>>("$id/")

    override suspend fun queryPlaces(page: Long, placeQueryParams: PlaceQueryParams) =
        get<ApiResponse<PagingResponse<PlaceResponse>>> {
            parameter("page", page.toString())
            parameter("size", 10)
            parameter("keyword", placeQueryParams.filterParams.keyword)
            placeQueryParams.filterParams.categoryIds?.forEach { parameter("category_id", it) }
            parameter("latitude", placeQueryParams.filterParams.latitude)
            parameter("longitude", placeQueryParams.filterParams.longitude)
            parameter("distance", placeQueryParams.filterParams.distance)
            parameter("courseId", placeQueryParams.filterParams.courseId)
        }

}