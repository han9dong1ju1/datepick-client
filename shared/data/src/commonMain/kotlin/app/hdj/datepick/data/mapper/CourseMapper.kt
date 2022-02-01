package app.hdj.datepick.data.mapper

import app.hdj.datepick.CourseEntity
import app.hdj.datepick.data.entity.course.CourseTagData
import app.hdj.datepick.data.entity.user.UserData
import app.hdj.datepick.domain.model.course.Course
import app.hdj.datepick.domain.model.course.CourseTag
import app.hdj.datepick.domain.model.user.User
import kotlinx.datetime.Instant

object CourseMapper : Mapper<CourseEntity, Course> {

    override fun CourseEntity.asDomain() = object : Course {
        override val id: Long = this@asDomain.id
        override val title: String = this@asDomain.title
        override val meetAt: String = this@asDomain.meetAt
        override val imageUrl: String? = this@asDomain.imageUrl
        override val isPrivate: Boolean = this@asDomain.isPrivate
        override val viewCount: Long = this@asDomain.viewCount
        override val pickCount: Long = this@asDomain.pickCount
        override val isPicked: Boolean = this@asDomain.isPicked
        override val user: User = this@asDomain.user
        override val tags: List<CourseTag> = this@asDomain.tags
    }

    override fun Course.asTable(): CourseEntity =
        CourseEntity(
            id,
            title,
            meetAt,
            imageUrl,
            isPrivate,
            viewCount,
            pickCount,
            isPicked,
            user.run { UserData(id, nickname, imageUrl, gender) },
            tags.map { CourseTagData(it.id, it.name) }
        )

}