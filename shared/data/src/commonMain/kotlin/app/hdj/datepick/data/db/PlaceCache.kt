package app.hdj.datepick.data.db

import app.hdj.datepick.PlaceTable
import app.hdj.datepick.PlaceTableQueries
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
class PlaceCache(override val query: PlaceTableQueries) : Cache<PlaceTable, PlaceTableQueries> {

    override fun getById(id: String): PlaceTable? {
        return query.getById(id).executeAsOneOrNull()
    }

    override fun save(data: PlaceTable) {
        query.insert(data)
    }

    override fun delete(id: String) {
        query.deleteById(id)
    }

}