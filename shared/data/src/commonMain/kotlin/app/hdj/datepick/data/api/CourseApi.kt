package app.hdj.datepick.data.api

import app.hdj.datepick.data.entity.course.CourseResponse
import app.hdj.datepick.utils.Inject
import app.hdj.datepick.utils.Singleton
import io.ktor.client.*
import io.ktor.client.request.*

@Singleton
class CourseApi @Inject constructor(override val client: HttpClient) : Api {

    override val basePath: String get() = "/course"

    suspend fun getById(id : String) = client.get<CourseResponse>("/$id")

}