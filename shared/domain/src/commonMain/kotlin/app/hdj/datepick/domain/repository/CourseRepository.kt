package app.hdj.datepick.domain.repository

import app.hdj.datepick.domain.StateData
import app.hdj.datepick.domain.model.course.Course
import kotlinx.coroutines.flow.Flow

interface CourseRepository {

    fun getById(id: String) : Flow<StateData<Course>>

}