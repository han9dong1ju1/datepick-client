package app.hdj.datepick.data.datastore

import app.hdj.datepick.PlaceEntity
import app.hdj.datepick.PlaceEntityQueries
import app.hdj.datepick.utils.Inject
import app.hdj.datepick.utils.Singleton
import kotlin.time.ExperimentalTime

interface PlaceDataStore : DataStore<PlaceEntity> {

    fun getById(id: Long): PlaceEntity?

    suspend fun saveAll(places: List<PlaceEntity>)

    fun delete(id: Long)

}

@OptIn(ExperimentalTime::class)
@Singleton
class PlaceDataStoreImp @Inject constructor(
    private val queries: PlaceEntityQueries
) : PlaceDataStore {

    override fun getById(id: Long): PlaceEntity? {
        return queries.getById(id).executeAsOneOrNull()
    }

    override suspend fun save(data: PlaceEntity) {
        queries.insert(data)
    }

    override suspend fun saveAll(places: List<PlaceEntity>) {
        places.forEach { save(it) }
    }

    override fun delete(id: Long) {
        queries.deleteById(id)
    }

}