package app.hdj.datepick.data.datastore

import app.hdj.datepick.CourseEntity
import app.hdj.datepick.CourseEntityQueries
import app.hdj.datepick.utils.di.Inject
import app.hdj.datepick.utils.di.Singleton
import kotlin.time.ExperimentalTime

interface CourseDataStore : DataStore<CourseEntity> {
    suspend fun get(id: Long): CourseEntity?
    suspend fun saveAll(courses: List<CourseEntity>)
}

@OptIn(ExperimentalTime::class)
@Singleton
class CourseDataStoreImp @Inject constructor(
    private val courseEntityQueries: CourseEntityQueries
) : CourseDataStore {

    override suspend fun get(id: Long): CourseEntity? {
        return courseEntityQueries.getById(id).executeAsOneOrNull()
    }

    override suspend fun save(data: CourseEntity) {
        courseEntityQueries.insert(data)
    }

    override suspend fun saveAll(courses: List<CourseEntity>) {
        courses.forEach { save(data = it) }
    }
}