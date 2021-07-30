package app.hdj.shared.client.data.repo

import app.hdj.shared.client.data.api.CourseApi
import app.hdj.shared.client.data.cache.CourseCache
import app.hdj.shared.client.domain.StateData
import app.hdj.shared.client.domain.entity.Course
import app.hdj.shared.client.domain.entity.Featured
import app.hdj.shared.client.domain.repo.CourseRepository
import kotlinx.coroutines.flow.Flow

open class CourseRepositoryImp(
    private val courseCache: CourseCache,
    private val courseApi: CourseApi,
) : CourseRepository {

    override fun getFeaturedCourses(): Flow<StateData<Featured>> {
        TODO("Not yet implemented")
    }

    override fun getCourse(id: String): Flow<StateData<Course>> {
        TODO("Not yet implemented")
    }

}