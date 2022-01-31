package app.hdj.datepick.domain.usecase.course

import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.model.course.Course
import app.hdj.datepick.domain.usecase.UseCase
import app.hdj.datepick.utils.Inject
import app.hdj.datepick.utils.Singleton
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Singleton
class GetCoursesFromFeaturedUseCase @Inject constructor() : UseCase<Long, Flow<LoadState<List<Course>>>> {
    override fun invoke(input: Long): Flow<LoadState<List<Course>>> = flow {
        emit(LoadState.Loading())
        delay(1000)
        emit(LoadState.Success(listOf()))
    }
}