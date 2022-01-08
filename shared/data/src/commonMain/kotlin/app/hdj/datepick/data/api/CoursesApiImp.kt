package app.hdj.datepick.data.api

import app.hdj.datepick.data.entity.course.CourseMetaResponse
import app.hdj.datepick.data.request.course.CourseCommitRequest
import app.hdj.datepick.utils.Inject
import app.hdj.datepick.utils.Singleton
import io.ktor.client.*

interface CoursesApi : Api {

    override val basePath: String get() = "/api/v1/courses"

    suspend fun getById(id : Long) : ApiResponse<CourseMetaResponse>

    suspend fun createCourse(courseCommitRequest: CourseCommitRequest) : ApiResponse<CourseMetaResponse>

}

@Singleton
class CoursesApiImp @Inject constructor(override val client: HttpClient) : CoursesApi {

    override suspend fun getById(id: Long) = get<ApiResponse<CourseMetaResponse>>("/$id")

    override suspend fun createCourse(courseCommitRequest: CourseCommitRequest): ApiResponse<CourseMetaResponse> {
        TODO("Not yet implemented")
    }

}