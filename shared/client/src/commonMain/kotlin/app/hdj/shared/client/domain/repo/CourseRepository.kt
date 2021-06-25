package app.hdj.shared.client.domain.repo

import app.hdj.shared.client.domain.StateData
import app.hdj.shared.client.domain.entity.Course
import kotlinx.coroutines.flow.Flow

interface CourseRepository {

    fun getCourse(id: String): Flow<StateData<Course>>

}