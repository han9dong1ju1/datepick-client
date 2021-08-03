package app.hdj.datepick.data.repository

import app.hdj.datepick.CourseTable
import app.hdj.datepick.UserTable
import app.hdj.datepick.data.api.CourseApi
import app.hdj.datepick.data.db.CourseCache
import app.hdj.datepick.data.entity.CourseResponse
import app.hdj.datepick.data.entity.UserResponse
import app.hdj.datepick.data.mapper.CourseMapper
import app.hdj.datepick.data.mapper.Mapper
import app.hdj.datepick.data.mapper.UserMapper
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
    private val cache: CourseCache
) : CourseRepository, Mapper<CourseTable, Course> by CourseMapper {

    override fun getById(id: String): Flow<StateData<Course>> = flow {
        emit(StateData.loading())

        val state = api
            .runCatching { getById(id) }
            .onSuccess { cache.save(CourseMapper.map(it)) }
            .fold(
                { StateData.success<Course>(it) },
                { StateData.failed(it, cache.getById(id)?.let(::map)) }
            )

        emit(state)
    }

}