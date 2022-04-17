package app.hdj.datepick.data.repository

import app.hdj.datepick.data.mapper.CourseMapper
import app.hdj.datepick.data.mapper.Mapper
import app.hdj.datepick.data.model.response.course.CourseResponse
import app.hdj.datepick.data.remote.PagingResponse
import app.hdj.datepick.data.remote.api.CourseApi
import app.hdj.datepick.data.remote.map
import app.hdj.datepick.data.utils.MockResponses
import app.hdj.datepick.data.utils.createPager
import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.LoadState.Companion.failed
import app.hdj.datepick.domain.LoadState.Companion.loading
import app.hdj.datepick.domain.LoadState.Companion.success
import app.hdj.datepick.domain.model.course.Course
import app.hdj.datepick.domain.repository.CourseRepository
import app.hdj.datepick.domain.usecase.course.params.CourseQueryParams
import app.hdj.datepick.utils.di.Inject
import app.hdj.datepick.utils.di.Singleton
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Singleton
class CourseRepositoryImp @Inject constructor(
    private val api: CourseApi
) : CourseRepository, Mapper<CourseResponse, Course> by CourseMapper {

    override fun getById(id: Long) = flow<LoadState<Course>> {
        emit(loading())
        api.runCatching { getById(id) }
            .onFailure { emit(failed(it)) }
            .onSuccess { emit(success(it.data.dataToDomain())) }
    }

    override fun create(title: String, region: String): Flow<LoadState<Course>> {
        TODO("Not yet implemented")
    }

    override fun getPagedMyDateCourses(params: CourseQueryParams) = createPager { page ->
        val apiResponse = api.queryMyDateCourses(page, params).data
        apiResponse.map { it.dataToDomain() }
    }

    override fun queryPagedCourses(params: CourseQueryParams) = createPager { page ->
//        val pagingResponse = api.queryCourses(page, params).data
//        pagingResponse.map { it.dataToDomain() }
        PagingResponse(
            count = 1000,
            currentPage = page,
            isLastPage = false,
            content = (0..9).map { MockResponses.course("2022", "04", "14") }
        ).map { it.dataToDomain() }
    }

    override fun queryFirstPageCourses(params: CourseQueryParams) = flow<LoadState<List<Course>>> {
        emit(loading())
        delay(1000)
        emit(success((0..9).map { MockResponses.course("2022", "04", "14").dataToDomain() }))
//        emit(loading())
//        api.runCatching { queryCourses(0, params) }
//            .onFailure { emit(failed(it)) }
//            .onSuccess { emit(success(it.data.content.mapDomain())) }
    }
}