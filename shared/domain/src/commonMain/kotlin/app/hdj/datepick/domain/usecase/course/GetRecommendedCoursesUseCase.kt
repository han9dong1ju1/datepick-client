package app.hdj.datepick.domain.usecase.course

import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.model.course.Course
import app.hdj.datepick.domain.model.course.CourseTag
import app.hdj.datepick.domain.model.user.User
import app.hdj.datepick.domain.model.user.UserGender
import app.hdj.datepick.domain.repository.CourseRepository
import app.hdj.datepick.domain.usecase.UseCase
import app.hdj.datepick.domain.usecase.course.params.CourseQueryParams
import app.hdj.datepick.utils.di.Inject
import app.hdj.datepick.utils.di.Singleton
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.random.Random

@Singleton
class GetRecommendedCoursesUseCase @Inject constructor(
    private val courseRepository: CourseRepository
) : UseCase<Unit, Flow<LoadState<List<Course>>>> {
    override fun invoke(input: Unit): Flow<LoadState<List<Course>>> =
        courseRepository.queryTenCourses(
            CourseQueryParams(
                CourseQueryParams.PagingParams(),
                CourseQueryParams.FilterParams(
                    tagIds = listOf(2, 4)
                )
            )
        )
}