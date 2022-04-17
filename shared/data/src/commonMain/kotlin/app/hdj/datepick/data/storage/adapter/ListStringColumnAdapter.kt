package app.hdj.datepick.data.storage.adapter

import com.squareup.sqldelight.ColumnAdapter
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object ListStringColumnAdapter : ColumnAdapter<List<String>, String> {
    override fun decode(databaseValue: String): List<String> {
        return Json.decodeFromString(databaseValue)
    }

    override fun encode(value: List<String>): String {
        return Json.encodeToString(value)
    }

}