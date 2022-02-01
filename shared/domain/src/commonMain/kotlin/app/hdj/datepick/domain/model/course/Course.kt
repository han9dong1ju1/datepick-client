package app.hdj.datepick.domain.model.course

import app.hdj.datepick.domain.model.user.User
import kotlinx.datetime.Instant

interface Course {

    val id: Long
    val title: String
    val meetAt: String
    val imageUrl: String?
    val isPrivate: Boolean
    val viewCount: Long
    val pickCount: Long
    val isPicked: Boolean
    val user: User
    val tags: List<CourseTag>

    val meetAtInstant: Instant
        get() = Instant.parse(meetAt)

}