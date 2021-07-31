package app.hdj.shared.client.domain.repo

import app.hdj.shared.client.domain.StateData
import app.hdj.shared.client.domain.entity.CourseDetail
import app.hdj.shared.client.domain.entity.CourseMetadata
import app.hdj.shared.client.domain.entity.Featured
import kotlinx.coroutines.flow.Flow

interface CourseRepository {

    fun getCourseMetadata(id: String): Flow<StateData<CourseMetadata>>

    fun getCourseDetail(id: String): Flow<StateData<CourseDetail>>

    fun getFeaturedCourses() : Flow<StateData<Featured>>

}