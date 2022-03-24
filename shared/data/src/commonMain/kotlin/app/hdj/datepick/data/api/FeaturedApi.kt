package app.hdj.datepick.data.api

import app.hdj.datepick.data.entity.featured.FeaturedResponse
import app.hdj.datepick.utils.di.Inject
import app.hdj.datepick.utils.di.Singleton
import io.ktor.client.*
import io.ktor.client.request.*

interface FeaturedApi : Api {
    override val basePath: String get() = "/api/v1/featured"

    suspend fun getPagedFeatured(
        page: Long,
        size: Long,
        isPinned: Boolean,
        courseId: Long?
    ): ApiResponse<PagingResponse<FeaturedResponse>>

    suspend fun getFeaturedDetail(id: Long): ApiResponse<FeaturedResponse>

}

@Singleton
class FeaturedApiImp @Inject constructor(
    override val client: HttpClient
) : FeaturedApi {

    override suspend fun getPagedFeatured(
        page: Long,
        size: Long,
        isPinned: Boolean,
        courseId: Long?
    ) = get<ApiResponse<PagingResponse<FeaturedResponse>>> {
        parameter("page", page.toString())
        parameter("size", size.toString())
        parameter("is_pinned", isPinned.toString())
        parameter("course_id", courseId?.toString())
    }

    override suspend fun getFeaturedDetail(id: Long) = get<ApiResponse<FeaturedResponse>>("$id/")

}