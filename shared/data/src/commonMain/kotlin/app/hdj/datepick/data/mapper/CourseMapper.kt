package app.hdj.datepick.data.mapper

import app.hdj.datepick.CourseEntity
import app.hdj.datepick.domain.model.course.Course
import kotlinx.datetime.Clock

object CourseMapper : Mapper<CourseEntity, Course> {

    override fun CourseEntity.asDomain() = object : Course {
        override val id: String = this@asDomain.id
        override val name: String = this@asDomain.name
    }

    override fun Course.asTable(): CourseEntity =
        CourseEntity(id, name, Clock.System.now().epochSeconds)

}