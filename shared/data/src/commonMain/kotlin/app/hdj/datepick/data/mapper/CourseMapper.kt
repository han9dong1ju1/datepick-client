package app.hdj.datepick.data.mapper

import app.hdj.datepick.CourseEntity
import app.hdj.datepick.domain.model.course.Course
import kotlinx.datetime.Clock

object CourseMapper : Mapper<CourseEntity, Course> {

    override fun CourseEntity.asDomain() = object : Course {
        override val id: Long = this@asDomain.id
        override val title: String = this@asDomain.title
        override val region: String = this@asDomain.region
        override val expectedAt: String = this@asDomain.expectedAt
        override val pickCount: Long = this@asDomain.pickCount
        override val importCount: Long = this@asDomain.importCount
        override val userId: Long = this@asDomain.userId
        override val thumbnailUrl: String? = this@asDomain.thumbnailUrl
    }

    override fun Course.asTable(): CourseEntity =
        CourseEntity(id, title, region, expectedAt, pickCount, importCount, userId, thumbnailUrl)

}