package app.hdj.shared.client.data.api

import app.hdj.shared.client.domain.entity.Course
import app.hdj.shared.client.domain.entity.Featured
import io.ktor.client.*
import io.ktor.client.request.*

open class CourseApi(val client: HttpClient) {

    suspend fun getFeaturedCourses(): Featured = client.get()

    suspend fun getCourse(courseId: String): Course = client.get()

}