package app.hdj.shared.client.data.api

import io.ktor.client.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DaggerCourseApi @Inject constructor(client: HttpClient) : CourseApi(client)