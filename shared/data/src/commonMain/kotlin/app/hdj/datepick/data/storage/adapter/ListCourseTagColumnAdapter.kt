package app.hdj.datepick.data.storage.adapter

import app.hdj.datepick.data.model.response.course.CourseTagResponse
import com.squareup.sqldelight.ColumnAdapter
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object ListCourseTagColumnAdapter : ColumnAdapter<List<CourseTagResponse>, String> {
    override fun decode(databaseValue: String): List<CourseTagResponse> {
        return Json.decodeFromString(databaseValue)
    }

    override fun encode(value: List<CourseTagResponse>): String {
        return Json.encodeToString(value)
    }

}