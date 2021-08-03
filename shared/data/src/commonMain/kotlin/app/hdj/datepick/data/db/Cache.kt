package app.hdj.datepick.data.db

import com.squareup.sqldelight.Transacter
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
interface Cache<Entity, Query : Transacter> {

    val query: Query

    fun getById(id: String): Entity?

    fun save(data : Entity)

    fun delete(id: String)

}