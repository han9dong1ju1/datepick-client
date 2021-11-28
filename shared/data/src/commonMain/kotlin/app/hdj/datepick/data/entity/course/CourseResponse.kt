package app.hdj.datepick.data.entity.course

import app.hdj.datepick.domain.model.course.Course
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CourseResponse(
    @SerialName("id") override val id: Long,
    @SerialName("title") override val title: String,
    @SerialName("region") override val region: String,
    @SerialName("expected_at") override val expectedAt: String,
    @SerialName("pick_count") override val pickCount: Long,
    @SerialName("import_count") override val importCount: Long,
    @SerialName("user_id") override val userId: Long,
    @SerialName("thumb_nail_url") override val thumbnailUrl: String?
) : Course