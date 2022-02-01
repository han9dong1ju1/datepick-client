package app.hdj.datepick.data.adapter

import app.hdj.datepick.data.entity.course.CourseTagData
import app.hdj.datepick.domain.model.course.CourseTag
import com.squareup.sqldelight.ColumnAdapter
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@OptIn(ExperimentalSerializationApi::class)
object ListCourseTagColumnAdapter : ColumnAdapter<List<CourseTagData>, String> {
    override fun decode(databaseValue: String): List<CourseTagData> {
        return Json.decodeFromString(databaseValue)
    }

    override fun encode(value: List<CourseTagData>): String {
        return Json.encodeToString(value)
    }

}