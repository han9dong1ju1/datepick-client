package app.hdj.datepick.domain.usecase.course

import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.model.course.Course
import app.hdj.datepick.domain.repository.CourseRepository
import app.hdj.datepick.domain.usecase.UseCase
import app.hdj.datepick.utils.di.Inject
import app.hdj.datepick.utils.di.Singleton
import kotlinx.coroutines.flow.Flow

@Singleton
class GetCourseDetailUseCase @Inject constructor(
    private val courseRepository: CourseRepository
) : UseCase<Long, Flow<LoadState<Course>>> {
    override fun invoke(input: Long) = courseRepository.getById(input)
}