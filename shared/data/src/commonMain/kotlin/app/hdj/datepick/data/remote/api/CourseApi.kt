package app.hdj.datepick.data.remote.api

import app.hdj.datepick.data.model.response.course.CourseResponse
import app.hdj.datepick.data.remote.Api
import app.hdj.datepick.data.remote.ApiResponse
import app.hdj.datepick.data.remote.PagingResponse
import app.hdj.datepick.data.remote.get
import app.hdj.datepick.data.model.request.course.CourseCommitRequest
import app.hdj.datepick.data.utils.MockResponses
import app.hdj.datepick.domain.usecase.course.params.CourseQueryParams
import app.hdj.datepick.utils.di.Inject
import app.hdj.datepick.utils.di.Singleton
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.delay
import kotlin.random.Random

fun fakeCourseApi(): CourseApi = object : CourseApi {

    override suspend fun getById(id: Long): ApiResponse<CourseResponse> {
        delay(1000)
        return ApiResponse(
            data = MockResponses.course("2022", (10..12).random().toString(), (10..28).random().toString()),
            error = null,
            message = null
        )
    }

    override suspend fun createCourse(courseCommitRequest: CourseCommitRequest): ApiResponse<CourseResponse> {
        delay(1000)
        return ApiResponse(
            data = MockResponses.course("2022", (10..12).random().toString(), (10..28).random().toString()),
            error = null,
            message = null
        )
    }

    override suspend fun queryMyDateCourses(
        page: Long,
        courseQueryParams: CourseQueryParams
    ): ApiResponse<PagingResponse<CourseResponse>> {
        delay(1000)
        return ApiResponse(
            data = MockResponses.coursePaged(page),
            error = null,
            message = null
        )
    }

    override suspend fun queryCourses(
        page: Long,
        courseQueryParams: CourseQueryParams
    ): ApiResponse<PagingResponse<CourseResponse>> {
        delay(1000)
        return ApiResponse(
            data = MockResponses.coursePaged(page),
            error = null,
            message = null
        )
    }

    override suspend fun queryPickedCourses(
        page: Long,
        courseQueryParams: CourseQueryParams
    ): ApiResponse<PagingResponse<CourseResponse>> {
        delay(1000)
        return ApiResponse(
            data = MockResponses.coursePaged(page),
            error = null,
            message = null
        )
    }
}

interface CourseApi : Api {

    override val basePath: String get() = "/v1/courses/"

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
class CourseApiImp @Inject constructor() : CourseApi {

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

    override suspend fun queryCourses(
        page: Long,
        courseQueryParams: CourseQueryParams
    ) = get<ApiResponse<PagingResponse<CourseResponse>>>() {
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