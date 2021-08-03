package app.hdj.datepick.data.repository

import app.hdj.datepick.data.api.CourseApi
import app.hdj.datepick.data.db.CourseCache
import app.hdj.datepick.data.mapper.CourseMapper
import app.hdj.datepick.domain.StateData
import app.hdj.datepick.domain.model.Course
import app.hdj.datepick.domain.repository.CourseRepository
import app.hdj.datepick.utils.Inject
import app.hdj.datepick.utils.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Singleton
class CourseRepositoryImp @Inject constructor(
    private val api: CourseApi,
    private val cache: CourseCache
) : CourseRepository {

    override fun getById(id: String): Flow<StateData<Course>> = with(CourseMapper) {
        flow {
            emit(StateData.loading())

            val state = api
                .runCatching { getById(id) }
                .onSuccess { cache.save(map(it)) }
                .fold(
                    { StateData.success(transfer(it)) },
                    { StateData.failed(it, cache.getById(id)?.let(::transfer)) }
                )

            emit(state)
        }
    }

}