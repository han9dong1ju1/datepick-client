package app.hdj.datepick.data.mapper

import app.hdj.datepick.CourseEntity
import app.hdj.datepick.data.entity.course.CourseResponse
import app.hdj.datepick.data.entity.course.CourseTagResponse
import app.hdj.datepick.data.entity.user.UserResponse
import app.hdj.datepick.domain.model.course.Course
import app.hdj.datepick.domain.model.course.CourseTag
import app.hdj.datepick.domain.model.user.User

object CourseMapper : Mapper<CourseEntity, CourseResponse, Course> {

    override fun CourseEntity.tableToDomain() = Course(
        id = id,
        title = title,
        meetAt = meetAt,
        imageUrl = imageUrl,
        isPrivate = isPrivate,
        viewCount = viewCount,
        pickCount = pickCount,
        isPicked = isPicked,
        user = user.run { User(id, nickname, imageUrl, gender) },
        tags = tags.map { it.run { CourseTag(id, name) } },
    )

    override fun CourseResponse.dataToDomain() = Course(
        id = id,
        title = title,
        meetAt = meetAt,
        imageUrl = imageUrl,
        isPrivate = isPrivate,
        viewCount = viewCount,
        pickCount = pickCount,
        isPicked = isPicked,
        user = user.run { User(id, nickname, imageUrl, gender) },
        tags = tags.map { it.run { CourseTag(id, name) } },
    )

    override fun Course.domainToTable() = CourseEntity(
        id,
        title,
        meetAt,
        imageUrl,
        isPrivate,
        viewCount,
        pickCount,
        isPicked,
        user.run { UserResponse(id, nickname, imageUrl, gender) },
        tags.map { CourseTagResponse(it.id, it.name) }
    )

    override fun CourseResponse.dataToTable(): CourseEntity = CourseEntity(
        id,
        title,
        meetAt,
        imageUrl,
        isPrivate,
        viewCount,
        pickCount,
        isPicked,
        user.run { UserResponse(id, nickname, imageUrl, gender) },
        tags.map { CourseTagResponse(it.id, it.name) }
    )
}