package app.hdj.datepick.data.datastore

import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
interface DataStore<Entity> {

    fun save(data : Entity)

}