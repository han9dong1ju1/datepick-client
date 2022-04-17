package app.hdj.datepick.data.mapper

import app.hdj.datepick.data.model.response.course.CourseResponse
import app.hdj.datepick.domain.model.course.Course
import app.hdj.datepick.domain.model.course.CourseTag
import app.hdj.datepick.domain.model.user.User

object CourseMapper : Mapper<CourseResponse, Course> {
    override fun CourseResponse.dataToDomain(): Course {
        return Course(
            id = id,
            title = title,
            meetAt = meetAt,
            imageUrl = imageUrl,
            isPrivate = isPrivate,
            viewCount = viewCount,
            pickCount = pickCount,
            isPicked = isPicked,
            user = User(
                id = user.id,
                nickname = user.nickname,
                imageUrl = user.imageUrl,
                gender = user.gender
            ),
            tags = tags.map { CourseTag(it.id, it.name) }
        )
    }
}