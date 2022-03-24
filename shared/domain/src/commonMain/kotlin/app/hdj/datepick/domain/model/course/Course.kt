package app.hdj.datepick.domain.model.course

import app.hdj.datepick.domain.model.user.User
import app.hdj.datepick.utils.Parcelable
import app.hdj.datepick.utils.Parcelize
import kotlinx.datetime.Instant

@Parcelize
data class Course(
    val id: Long,
    val title: String,
    val meetAt: String?,
    val imageUrl: String?,
    val isPrivate: Boolean,
    val viewCount: Long,
    val pickCount: Long,
    val isPicked: Boolean,
    val user: User,
    val tags: List<CourseTag>

) : Parcelable{

    val meetAtInstant: Instant?
        get() = meetAt?.let { Instant.parse(it) }

}