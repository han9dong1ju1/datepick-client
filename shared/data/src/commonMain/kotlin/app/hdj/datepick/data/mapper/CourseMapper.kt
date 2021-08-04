package app.hdj.datepick.data.mapper

import app.hdj.datepick.CourseTable
import app.hdj.datepick.data.entity.CourseResponse
import app.hdj.datepick.domain.model.course.Course
import kotlinx.datetime.Clock

object CourseMapper : Mapper<CourseTable, Course> {

    override fun CourseTable.asDomain() = object : Course {
        override val id: String = this@asDomain.id
        override val name: String = this@asDomain.name
    }

    override fun Course.asTable(): CourseTable =
        CourseTable(id, name, Clock.System.now().epochSeconds)

}