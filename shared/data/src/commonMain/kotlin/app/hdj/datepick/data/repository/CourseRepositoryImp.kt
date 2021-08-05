package app.hdj.datepick.data.repository

import app.hdj.datepick.CourseEntity
import app.hdj.datepick.data.api.CourseApi
import app.hdj.datepick.data.datastore.CourseDataStore
import app.hdj.datepick.data.mapper.CourseMapper
import app.hdj.datepick.data.mapper.Mapper
import app.hdj.datepick.domain.StateData
import app.hdj.datepick.domain.model.course.Course
import app.hdj.datepick.domain.repository.CourseRepository
import app.hdj.datepick.utils.Inject
import app.hdj.datepick.utils.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Singleton
class CourseRepositoryImp @Inject constructor(
    private val api: CourseApi,
    private val cache: CourseDataStore
) : CourseRepository, Mapper<CourseEntity, Course> by CourseMapper {

    override fun getById(id: String): Flow<StateData<Course>> = TODO()

}