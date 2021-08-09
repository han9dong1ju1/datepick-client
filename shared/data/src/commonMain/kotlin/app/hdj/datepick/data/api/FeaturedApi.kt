package app.hdj.datepick.data.api

import app.hdj.datepick.domain.model.featured.Featured
import app.hdj.datepick.utils.Inject
import app.hdj.datepick.utils.Singleton
import io.ktor.client.*

@Singleton
class FeaturedApi @Inject constructor(override val client: HttpClient) : Api {

    override val basePath: String = "api/v1/featured"

    suspend fun getFeatured() = get<ApiResponse<List<Featured>>>()

    suspend fun getFeaturedDetail(id: String) = get<ApiResponse<Featured>>("$id/")

}