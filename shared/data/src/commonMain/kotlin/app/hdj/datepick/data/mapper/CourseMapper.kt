package app.hdj.datepick.data.mapper

import app.hdj.datepick.CourseTable
import app.hdj.datepick.data.entity.CourseResponse
import app.hdj.datepick.domain.model.course.Course
import kotlinx.datetime.Clock

object CourseMapper : Mapper<CourseTable, Course> {

    override fun map(table: CourseTable) = object : Course {
        override val id: String = table.id
        override val name: String = table.name
    }

    override fun map(model: Course): CourseTable = with(model) {
        CourseTable(id, name, Clock.System.now().epochSeconds)
    }

}