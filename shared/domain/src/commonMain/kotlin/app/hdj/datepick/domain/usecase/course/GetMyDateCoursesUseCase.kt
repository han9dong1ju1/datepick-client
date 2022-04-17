package app.hdj.datepick.domain.usecase.course

import app.hdj.datepick.domain.repository.CourseRepository
import app.hdj.datepick.domain.repository.MeRepository
import app.hdj.datepick.domain.usecase.course.params.CourseQueryParams
import app.hdj.datepick.domain.usecase.course.params.filterParams
import app.hdj.datepick.utils.di.Inject
import app.hdj.datepick.utils.di.Singleton
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map

@Singleton
class GetMyDateCoursesUseCase @Inject constructor(
    private val courseRepository: CourseRepository,
    private val meRepository: MeRepository
) {

    operator fun invoke(input: CourseQueryParams) =
        meRepository.observableCache().map { me ->
            val queryParams = input.copy()
            queryParams.filterParams { userId = me?.id }
            courseRepository.getPagedMyDateCourses(queryParams)
        }

}