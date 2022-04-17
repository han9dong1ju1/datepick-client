package app.hdj.datepick.domain.usecase.course

import app.hdj.datepick.domain.model.course.Course
import app.hdj.datepick.domain.repository.CourseRepository
import app.hdj.datepick.domain.usecase.UseCase
import app.hdj.datepick.domain.usecase.course.params.CourseQueryParams
import app.hdj.datepick.utils.di.Inject
import app.hdj.datepick.utils.di.Singleton
import com.kuuurt.paging.multiplatform.Pager
import com.kuuurt.paging.multiplatform.PagingData
import kotlinx.coroutines.flow.Flow

@Singleton
class SearchCoursesUseCase @Inject constructor(private val courseRepository: CourseRepository) :
    UseCase<CourseQueryParams, Pager<Long, Course>> {

    override fun invoke(input: CourseQueryParams) = courseRepository.queryPagedCourses(input)


}