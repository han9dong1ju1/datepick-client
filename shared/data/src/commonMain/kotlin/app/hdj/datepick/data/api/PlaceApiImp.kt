package app.hdj.datepick.data.api

import app.hdj.datepick.data.entity.PlaceResponse
import app.hdj.datepick.domain.model.place.Place
import app.hdj.datepick.utils.Inject
import app.hdj.datepick.utils.Singleton
import io.ktor.client.*

interface PlaceApi : Api {

    override val basePath: String get() = "api/v1/place"

    suspend fun getById(id : Long) : ApiResponse<PlaceResponse>

}

@Singleton
class PlaceApiImp @Inject constructor(override val client: HttpClient) : PlaceApi {

    override suspend fun getById(id: Long) = get<ApiResponse<PlaceResponse>>("$id/")

}