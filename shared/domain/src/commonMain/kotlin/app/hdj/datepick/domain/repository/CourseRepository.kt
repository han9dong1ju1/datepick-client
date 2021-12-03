package app.hdj.datepick.domain.repository

import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.model.course.Course
import kotlinx.coroutines.flow.Flow

interface CourseRepository {

    fun getById(id: Long) : Flow<LoadState<Course>>

    fun create(
        title : String,
        region : String
    ) : Flow<LoadState<Course>>

}