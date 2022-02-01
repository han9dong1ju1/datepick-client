package app.hdj.datepick.data.repository

import app.hdj.datepick.CourseEntity
import app.hdj.datepick.data.api.CourseApi
import app.hdj.datepick.data.api.map
import app.hdj.datepick.data.datastore.CourseDataStore
import app.hdj.datepick.data.mapper.CourseMapper
import app.hdj.datepick.data.mapper.CourseMapper.asTable
import app.hdj.datepick.data.mapper.Mapper
import app.hdj.datepick.data.utils.createPagedDataFlow
import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.emitState
import app.hdj.datepick.domain.model.course.Course
import app.hdj.datepick.domain.repository.CourseRepository
import app.hdj.datepick.domain.usecase.course.params.CourseQueryParams
import app.hdj.datepick.utils.di.Inject
import app.hdj.datepick.utils.di.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Singleton
class CourseRepositoryImp @Inject constructor(
    private val api: CourseApi,
    private val dataStore: CourseDataStore
) : CourseRepository, Mapper<CourseEntity, Course> by CourseMapper {

    override fun getById(id: Long) = flow {
        val cached = dataStore.runCatching { get(id) }.getOrNull()
        emitState(cached?.asDomain()) {
            val data = api.getById(id).data
            dataStore.save(data.asTable())
            data
        }
    }

    override fun create(title: String, region: String): Flow<LoadState<Course>> {
        TODO("Not yet implemented")
    }

    override fun getPagedMyDateCourses(courseQueryParams: CourseQueryParams) = createPagedDataFlow<Course> { page ->
        val apiResponse = api.queryMyDateCourses(page, courseQueryParams).data
        dataStore.saveAll(apiResponse.content.mapTable())
        apiResponse.map { it }
    }

    override fun queryPagedCourses(courseQueryParams: CourseQueryParams) = createPagedDataFlow<Course> { page ->
        val apiResponse = api.queryCourses(page, courseQueryParams).data
        dataStore.saveAll(apiResponse.content.mapTable())
        apiResponse.map { it }
    }

    override fun queryTenCourses(courseQueryParams: CourseQueryParams) = flow<LoadState<List<Course>>> {
        emitState {
            val apiResponse = api.queryCourses(0, courseQueryParams).data
            dataStore.saveAll(apiResponse.content.mapTable())
            apiResponse.content
        }
    }
}