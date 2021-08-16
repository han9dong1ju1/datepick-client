package app.hdj.datepick.data.adapter

import com.squareup.sqldelight.ColumnAdapter
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@OptIn(ExperimentalSerializationApi::class)
object ListStringColumnAdapter : ColumnAdapter<List<String>, String> {
    override fun decode(databaseValue: String): List<String> {
        return Json.decodeFromString(databaseValue)
    }

    override fun encode(value: List<String>): String {
        return Json.encodeToString(value)
    }

}