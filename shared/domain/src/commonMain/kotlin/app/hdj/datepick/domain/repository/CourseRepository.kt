package app.hdj.datepick.domain.repository

import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.model.course.Course
import app.hdj.datepick.domain.usecase.course.params.CourseQueryParams
import com.kuuurt.paging.multiplatform.Pager
import com.kuuurt.paging.multiplatform.PagingData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow

interface CourseRepository {

    fun getById(id: Long): Flow<Course>

    fun create(title: String, region: String): Flow<Course>

    fun getPagedMyDateCourses(params: CourseQueryParams): Pager<Long, Course>

    fun queryPagedCourses(params: CourseQueryParams): Pager<Long, Course>

    fun queryFirstPageCourses(params: CourseQueryParams): Flow<List<Course>>

}