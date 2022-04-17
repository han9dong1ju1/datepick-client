package app.hdj.datepick.domain.usecase.course

import app.hdj.datepick.domain.repository.CourseRepository
import app.hdj.datepick.utils.di.Inject
import app.hdj.datepick.utils.di.Singleton

@Singleton
class GetCourseDetailUseCase @Inject constructor(
    private val courseRepository: CourseRepository
) {
    operator fun invoke(input: Long) = courseRepository.getById(input)
}