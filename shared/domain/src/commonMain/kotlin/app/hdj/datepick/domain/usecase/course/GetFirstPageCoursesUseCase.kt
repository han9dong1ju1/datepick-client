package app.hdj.datepick.domain.usecase.course

import app.hdj.datepick.domain.repository.CourseRepository
import app.hdj.datepick.domain.usecase.course.params.CourseQueryParams
import app.hdj.datepick.utils.di.Inject
import app.hdj.datepick.utils.di.Singleton

@Singleton
class GetFirstPageCoursesUseCase @Inject constructor(private val courseRepository: CourseRepository) {

    operator fun invoke(input: CourseQueryParams) = courseRepository.queryFirstPageCourses(input)

}