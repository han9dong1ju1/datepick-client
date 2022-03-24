package app.hdj.datepick.data.api

import app.hdj.datepick.data.entity.course.CourseResponse
import app.hdj.datepick.data.entity.featured.FeaturedResponse
import app.hdj.datepick.data.request.course.CourseCommitRequest
import app.hdj.datepick.data.utils.MockResponses
import app.hdj.datepick.domain.usecase.course.params.CourseQueryParams
import app.hdj.datepick.utils.di.Inject
import app.hdj.datepick.utils.di.Singleton
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.delay
import kotlin.random.Random

interface CourseApi : Api {

    override val basePath: String get() = "/v1/courses"

    suspend fun getById(id: Long): ApiResponse<CourseResponse>

    suspend fun createCourse(courseCommitRequest: CourseCommitRequest): ApiResponse<CourseResponse>

    suspend fun queryMyDateCourses(
        page: Long,
        courseQueryParams: CourseQueryParams
    ): ApiResponse<PagingResponse<CourseResponse>>

    suspend fun queryCourses(
        page: Long,
        courseQueryParams: CourseQueryParams
    ): ApiResponse<PagingResponse<CourseResponse>>

    suspend fun queryPickedCourses(
        page: Long,
        courseQueryParams: CourseQueryParams
    ): ApiResponse<PagingResponse<CourseResponse>>

}

@Singleton
class CourseApiImp @Inject constructor(override val client: HttpClient) : CourseApi {

    override suspend fun getById(id: Long) = get<ApiResponse<CourseResponse>>("/$id")

    override suspend fun createCourse(courseCommitRequest: CourseCommitRequest): ApiResponse<CourseResponse> {
        TODO("Not yet implemented")
    }

//    override suspend fun getMyDateCourses(
//        page: Long
//    ): ApiResponse<PagingResponse<CourseData>> = get("/me") {
//        if (page != 0L) parameter("page", page.toString())
//        parameter("size", 10)
//    }

    override suspend fun queryMyDateCourses(
        page: Long,
        courseQueryParams: CourseQueryParams
    ): ApiResponse<PagingResponse<CourseResponse>> {
        delay(2000)
        if (Random.nextBoolean()) throw Exception("Error!")
        return ApiResponse(null, null, MockResponses.coursePaged(page))
    }

    /*
    * **Paging Params**

- `sort` (String, Enum)
    - `latest` - 최신 순 (기본)
    - `pick` - 픽 많은 순
    - `popular` - 인기순

**Filter Params**

- `keyword` (String)
- `tag_id` (List<Byte>)
- `featured_id` (Long)
- `place_id` (Long)
- `user_id` (Long)
    *
    * */
    override suspend fun queryCourses(
        page: Long,
        courseQueryParams: CourseQueryParams
    ) = get<ApiResponse<PagingResponse<CourseResponse>>> {
        parameter("page", page.toString())
        parameter("size", 10)
        parameter("featured_id", courseQueryParams.filterParams.featuredId)
        parameter("place_id", courseQueryParams.filterParams.placeId)
        parameter("user_id", courseQueryParams.filterParams.userId)
        parameter("keyword", courseQueryParams.filterParams.keyword)
        courseQueryParams.filterParams.tagIds?.forEach {
            parameter("tag_id", it)
        }

    }

    override suspend fun queryPickedCourses(
        page: Long,
        courseQueryParams: CourseQueryParams
    ) = ApiResponse(null, null, MockResponses.coursePaged(page))


}