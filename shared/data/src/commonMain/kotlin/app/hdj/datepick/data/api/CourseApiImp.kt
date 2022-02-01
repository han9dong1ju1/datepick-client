package app.hdj.datepick.data.api

import app.hdj.datepick.data.entity.course.CourseData
import app.hdj.datepick.data.entity.course.CourseTagData
import app.hdj.datepick.data.entity.user.UserData
import app.hdj.datepick.data.request.course.CourseCommitRequest
import app.hdj.datepick.data.utils.MockResponses
import app.hdj.datepick.domain.model.user.UserGender
import app.hdj.datepick.domain.usecase.course.params.CourseQueryParams
import app.hdj.datepick.utils.di.Inject
import app.hdj.datepick.utils.di.Singleton
import io.ktor.client.*
import kotlinx.coroutines.delay
import kotlin.random.Random

interface CourseApi : Api {

    override val basePath: String get() = "/api/v1/courses"

    suspend fun getById(id: Long): ApiResponse<CourseData>

    suspend fun createCourse(courseCommitRequest: CourseCommitRequest): ApiResponse<CourseData>

    suspend fun queryMyDateCourses(
        page: Long,
        courseQueryParams: CourseQueryParams
    ): ApiResponse<PagingResponse<CourseData>>

    suspend fun queryCourses(
        page: Long,
        courseQueryParams: CourseQueryParams
    ): ApiResponse<PagingResponse<CourseData>>

    suspend fun queryPickedCourses(
        page: Long,
        courseQueryParams: CourseQueryParams
    ): ApiResponse<PagingResponse<CourseData>>

}

@Singleton
class CourseApiImp @Inject constructor(override val client: HttpClient) : CourseApi {

    override suspend fun getById(id: Long) = get<ApiResponse<CourseData>>("/$id")

    override suspend fun createCourse(courseCommitRequest: CourseCommitRequest): ApiResponse<CourseData> {
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
    ): ApiResponse<PagingResponse<CourseData>> {
        delay(2000)
        if (Random.nextBoolean()) throw Exception("Error!")
        return ApiResponse(null, null, MockResponses.coursePaged(page))
    }

    override suspend fun queryCourses(
        page: Long,
        courseQueryParams: CourseQueryParams
    ) = ApiResponse(null, null, MockResponses.coursePaged(page))

    override suspend fun queryPickedCourses(
        page: Long,
        courseQueryParams: CourseQueryParams
    ) = ApiResponse(null, null, MockResponses.coursePaged(page))


}