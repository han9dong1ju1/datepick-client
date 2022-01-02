package app.hdj.datepick.data.api

import app.hdj.datepick.data.entity.featured.FeaturedDetailResponse
import app.hdj.datepick.data.entity.featured.FeaturedResponse
import app.hdj.datepick.utils.Inject
import app.hdj.datepick.utils.Singleton
import io.ktor.client.*
import io.ktor.client.call.*

interface FeaturedApi : Api {
    override val basePath: String get() = "/api/v1/featured"

    suspend fun getFeatured() : ApiResponse<List<FeaturedResponse>>
    suspend fun getFeaturedDetail(id: Long) : ApiResponse<FeaturedDetailResponse>
}

@Singleton
class FeaturedApiImp @Inject constructor(
    override val client: HttpClient
) : FeaturedApi {

    override suspend fun getFeatured() = get<ApiResponse<List<FeaturedResponse>>>()

    override suspend fun getFeaturedDetail(id: Long) = get<ApiResponse<FeaturedDetailResponse>>("$id/")

}