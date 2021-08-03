package app.hdj.datepick.data.mapper

import app.hdj.datepick.CourseTable
import app.hdj.datepick.data.entity.CourseResponse
import app.hdj.datepick.domain.model.course.Course
import kotlinx.datetime.Clock

object CourseMapper : Mapper<CourseTable, CourseResponse> {

    override fun map(table: CourseTable): CourseResponse = with(table) {
        CourseResponse(id, name)
    }

    override fun map(response: CourseResponse): CourseTable = with(response) {
        CourseTable(id, name, Clock.System.now().epochSeconds)
    }

}