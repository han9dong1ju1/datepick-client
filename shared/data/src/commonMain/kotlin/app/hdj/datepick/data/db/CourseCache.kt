package app.hdj.datepick.data.db

import app.hdj.datepick.CourseTable
import app.hdj.datepick.CourseTableQueries
import app.hdj.datepick.data.entity.CourseResponse
import app.hdj.datepick.utils.Inject
import app.hdj.datepick.utils.Singleton
import kotlinx.datetime.Clock
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
@Singleton
class CourseCache @Inject constructor(override val query: CourseTableQueries) :
    Cache<CourseTable, CourseTableQueries> {

    override fun getById(id: String): CourseTable? {
        return query.getById(id).executeAsOneOrNull()
    }

    override fun save(data : CourseTable) {
        query.insert(data)
    }

    override fun delete(id: String) {
        query.deleteById(id)
    }

}