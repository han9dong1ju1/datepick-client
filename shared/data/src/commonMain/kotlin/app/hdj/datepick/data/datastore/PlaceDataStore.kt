package app.hdj.datepick.data.datastore

import app.hdj.datepick.PlaceEntity
import kotlin.time.ExperimentalTime

interface PlaceDataStore : DataStore<PlaceEntity> {

    fun findById(id : String) : PlaceEntity?

}

@OptIn(ExperimentalTime::class)
class PlaceDataStoreImp(

) : PlaceDataStore {

    override fun findById(id: String): PlaceEntity? {
        TODO("Not yet implemented")
    }

    override suspend fun save(data: PlaceEntity) {

    }

}