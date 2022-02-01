package app.hdj.datepick.data.entity.course

import app.hdj.datepick.data.entity.user.UserData
import app.hdj.datepick.domain.model.course.Course
import app.hdj.datepick.domain.model.course.CourseTag
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CourseData(
    @SerialName("id") override val id: Long,
    @SerialName("title") override val title: String,
    @SerialName("meet_at") override val meetAt: String,
    @SerialName("image_url") override val imageUrl: String?,
    @SerialName("is_private") override val isPrivate: Boolean,
    @SerialName("view_count") override val viewCount: Long,
    @SerialName("pick_count") override val pickCount: Long,
    @SerialName("is_picked") override val isPicked: Boolean,
    @SerialName("user") override val user: UserData,
    @SerialName("tags") override val tags: List<CourseTagData>
) : Course

@Serializable
data class CourseTagData(
    @SerialName("id") override val id: Long,
    @SerialName("name") override val name: String
) : CourseTag