package app.hdj.datepick.data.datastore

import app.hdj.datepick.PlaceEntity
import app.hdj.datepick.PlaceEntityQueries
import kotlin.time.ExperimentalTime

interface PlaceDataStore : DataStore<PlaceEntity> {

    fun getById(id: Long): PlaceEntity?

    fun delete(id: Long)

}

@OptIn(ExperimentalTime::class)
class PlaceDataStoreImp(
    private val queries: PlaceEntityQueries
) : PlaceDataStore {

    override fun getById(id: Long): PlaceEntity? {
        return queries.getById(id).executeAsOneOrNull()
    }

    override suspend fun save(data: PlaceEntity) {
        queries.insert(data)
    }

    override fun delete(id: Long) {
        queries.deleteById(id)
    }

}