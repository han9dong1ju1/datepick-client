package app.hdj.datepick.data.mapper

import app.hdj.datepick.CourseTable
import app.hdj.datepick.data.entity.CourseResponse
import app.hdj.datepick.domain.model.Course
import kotlinx.datetime.Clock

object CourseMapper : Mapper<CourseTable, CourseResponse, Course> {

    override fun map(table: CourseTable): CourseResponse = with(table) {
        CourseResponse(id, name)
    }

    override fun map(response: CourseResponse): CourseTable = with(response) {
        CourseTable(id, name, Clock.System.now().epochSeconds)
    }

    override fun transfer(response: CourseResponse): Course = with(response) {
        Course(id, name)
    }

    override fun transfer(table: CourseTable): Course = with(table) {
        Course(id, name)
    }

}