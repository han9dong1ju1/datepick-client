package app.hdj.datepick.data.repository

import app.hdj.datepick.CourseEntity
import app.hdj.datepick.data.api.CourseApi
import app.hdj.datepick.data.api.map
import app.hdj.datepick.data.datastore.CourseDataStore
import app.hdj.datepick.data.entity.course.CourseResponse
import app.hdj.datepick.data.mapper.CourseMapper
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
) : CourseRepository, Mapper<CourseEntity, CourseResponse, Course> by CourseMapper {

    override fun getById(id: Long) = flow {
        val cached = dataStore.runCatching { get(id) }.getOrNull()
        emitState(cached?.tableToDomain()) {
            val data = api.getById(id).data
            dataStore.save(data.dataToTable())
            data.dataToDomain()
        }
    }

    override fun create(title: String, region: String): Flow<LoadState<Course>> {
        TODO("Not yet implemented")
    }

    override fun getPagedMyDateCourses(courseQueryParams: CourseQueryParams) = createPagedDataFlow { page ->
        val apiResponse = api.queryMyDateCourses(page, courseQueryParams).data
        dataStore.saveAll(apiResponse.content.mapTable())
        apiResponse.map { it.dataToDomain() }
    }

    override fun queryPagedCourses(courseQueryParams: CourseQueryParams) = createPagedDataFlow { page ->
        val apiResponse = api.queryCourses(page, courseQueryParams).data
        dataStore.saveAll(apiResponse.content.mapTable())
        apiResponse.map { it.dataToDomain() }
    }

    override fun queryTenCourses(courseQueryParams: CourseQueryParams) = flow {
        emitState {
            val apiResponse = api.queryCourses(0, courseQueryParams).data
            dataStore.saveAll(apiResponse.content.mapTable())
            apiResponse.content.mapDomain()
        }
    }
}