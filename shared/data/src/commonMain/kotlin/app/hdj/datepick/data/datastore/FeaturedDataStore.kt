package app.hdj.datepick.data.datastore

import app.hdj.datepick.FeaturedEntity
import app.hdj.datepick.FeaturedEntityQueries
import app.hdj.datepick.utils.Inject
import app.hdj.datepick.utils.Singleton
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.Flow

interface FeaturedDataStore : DataStore<FeaturedEntity> {

    fun findAllCached(): List<FeaturedEntity>
    suspend fun saveAll(list: List<FeaturedEntity>)

}

@Singleton
class FeaturedDataStoreImp @Inject constructor(
    private val queries: FeaturedEntityQueries
) : FeaturedDataStore {

    override fun findAllCached() = queries.findAll().executeAsList()

    override suspend fun save(data: FeaturedEntity) {
        queries.insert(data)
    }

    override suspend fun saveAll(list: List<FeaturedEntity>) {
        queries.deleteAll()
        list.forEach { save(data = it) }
    }

}