package app.hdj.shared.client.data.repo

import app.hdj.shared.client.data.api.CourseApi
import app.hdj.shared.client.data.cache.CourseCache
import app.hdj.shared.client.domain.StateData
import app.hdj.shared.client.domain.entity.CourseDetail
import app.hdj.shared.client.domain.entity.CourseMetadata
import app.hdj.shared.client.domain.entity.Featured
import app.hdj.shared.client.domain.repo.CourseRepository
import kotlinx.coroutines.flow.Flow

open class CourseRepositoryImp(
    private val courseCache: CourseCache,
    private val courseApi: CourseApi,
) : CourseRepository {

    override fun getCourseDetail(id: String): Flow<StateData<CourseDetail>> {
        TODO("Not yet implemented")
    }

    override fun getFeaturedCourses(): Flow<StateData<Featured>> {
        TODO("Not yet implemented")
    }

    override fun getCourseMetadata(id: String): Flow<StateData<CourseMetadata>> {
        TODO("Not yet implemented")
    }

}