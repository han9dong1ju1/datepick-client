@file:OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)

package app.hdj.datepick.domain.repository

import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.model.course.Course
import app.hdj.datepick.domain.usecase.course.params.CourseQueryParams
import com.kuuurt.paging.multiplatform.PagingData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow

interface CourseRepository {

    fun getById(id: Long): Flow<LoadState<Course>>

    fun create(title: String, region: String): Flow<LoadState<Course>>

    fun getPagedMyDateCourses(courseQueryParams: CourseQueryParams): Flow<PagingData<Course>>

    fun queryTenCourses(courseQueryParams: CourseQueryParams): Flow<LoadState<List<Course>>>

    fun queryPagedCourses(courseQueryParams: CourseQueryParams): Flow<PagingData<Course>>

}