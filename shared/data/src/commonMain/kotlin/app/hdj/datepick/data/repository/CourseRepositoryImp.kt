package app.hdj.datepick.data.repository

import app.hdj.datepick.data.mapper.CourseMapper
import app.hdj.datepick.data.mapper.Mapper
import app.hdj.datepick.data.model.response.course.CourseResponse
import app.hdj.datepick.data.remote.PagingResponse
import app.hdj.datepick.data.remote.api.CourseApi
import app.hdj.datepick.data.remote.map
import app.hdj.datepick.data.utils.MockResponses
import app.hdj.datepick.data.utils.createPager
import app.hdj.datepick.domain.model.course.Course
import app.hdj.datepick.domain.repository.CourseRepository
import app.hdj.datepick.domain.usecase.course.params.CourseQueryParams
import app.hdj.datepick.utils.di.Inject
import app.hdj.datepick.utils.di.Named
import app.hdj.datepick.utils.di.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Singleton
class CourseRepositoryImp @Inject constructor(
    @Named("mocked") private val api: CourseApi
) : CourseRepository, Mapper<CourseResponse, Course> by CourseMapper {

    override fun getById(id: Long) = flow {
        val response = api.getById(id)
        emit(response.data.asDomain())
    }

    override fun create(title: String, region: String): Flow<Course> {
        TODO("Not yet implemented")
    }

    override fun getPagedMyDateCourses(params: CourseQueryParams) = createPager { page ->
        val apiResponse = api.queryMyDateCourses(page, params).data
        apiResponse.map { it.asDomain() }
    }

    override fun queryPagedCourses(params: CourseQueryParams) = createPager { page ->
        val pagingResponse = api.queryCourses(page, params).data
        pagingResponse.map { it.asDomain() }
    }

    override fun queryFirstPageCourses(params: CourseQueryParams) = flow {
        val response = api.queryCourses(0, params).data
        emit(response.content.map { it.asDomain() })
    }
}