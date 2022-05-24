package app.hdj.datepick.data.remote.api

import app.hdj.datepick.data.model.request.place.PlaceAddRequest
import app.hdj.datepick.data.model.response.place.PlaceResponse
import app.hdj.datepick.data.remote.*
import app.hdj.datepick.data.utils.MockResponses
import app.hdj.datepick.domain.model.place.Place
import app.hdj.datepick.domain.usecase.place.params.PlaceQueryParams
import app.hdj.datepick.utils.di.Inject
import app.hdj.datepick.utils.di.Singleton
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.delay

fun fakePlaceApi(): PlaceApi = object : PlaceApi {

    override suspend fun getById(id: Long): ApiResponse<PlaceResponse> {
        delay(1000)
        return ApiResponse(
            data = MockResponses.place(),
            error = null,
            message = null
        )
    }

    override suspend fun queryPlaces(
        page: Long,
        placeQueryParams: PlaceQueryParams
    ): ApiResponse<PagingResponse<PlaceResponse>> {
        delay(1000)
        return ApiResponse(
            data = MockResponses.placePaged(page),
            error = null,
            message = null
        )
    }

    override suspend fun addPlace(request: PlaceAddRequest): ApiResponse<PlaceResponse> {
        delay(1000)
        return ApiResponse(
            data = MockResponses.place(),
            error = null,
            message = null
        )
    }

}

interface PlaceApi : Api {

    override val basePath: String get() = "/v1/places/"

    suspend fun getById(id: Long): ApiResponse<PlaceResponse>

    suspend fun queryPlaces(
        page: Long,
        placeQueryParams: PlaceQueryParams
    ): ApiResponse<PagingResponse<PlaceResponse>>

    suspend fun addPlace(request: PlaceAddRequest): ApiResponse<PlaceResponse>

}

@Singleton
class PlaceApiImp @Inject constructor() : PlaceApi {

    override suspend fun getById(id: Long) = get<ApiResponse<PlaceResponse>>("$id/")

    override suspend fun queryPlaces(page: Long, placeQueryParams: PlaceQueryParams) =
        get<ApiResponse<PagingResponse<PlaceResponse>>> {
            parameter("sort", placeQueryParams.pagingParams.sort.value)
            parameter("page", page.toString())
            parameter("size", 10)
            parameter("keyword", placeQueryParams.filterParams.keyword)
            placeQueryParams.filterParams.categoryIds?.forEach { parameter("category_id", it) }

            if (placeQueryParams.filterParams.latitude != null &&
                placeQueryParams.filterParams.longitude != null
            ) {
                parameter("latitude", placeQueryParams.filterParams.latitude)
                parameter("longitude", placeQueryParams.filterParams.longitude)
                parameter("distance", placeQueryParams.filterParams.distance)
            }
            
            parameter("course_id", placeQueryParams.filterParams.courseId)
        }

    override suspend fun addPlace(request: PlaceAddRequest): ApiResponse<PlaceResponse> = post {
        setBody(request)
    }
}