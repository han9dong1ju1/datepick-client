package app.hdj.datepick.data.datastore

import app.hdj.datepick.CourseEntity
import app.hdj.datepick.utils.Inject
import app.hdj.datepick.utils.Singleton
import kotlin.time.ExperimentalTime

interface CourseDataStore : DataStore<CourseEntity> {
    fun findById(id : String) : CourseEntity?
}

@OptIn(ExperimentalTime::class)
@Singleton
class CourseDataStoreImp @Inject constructor(

) : CourseDataStore {

    override fun findById(id: String): CourseEntity? {
        TODO("Not yet implemented")
    }

    override fun save(data: CourseEntity) {
        TODO("Not yet implemented")
    }

}