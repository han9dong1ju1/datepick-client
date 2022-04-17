package app.hdj.datepick.data.storage.datastore

import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
interface DataStore<Entity> {

    suspend fun save(data : Entity)

}