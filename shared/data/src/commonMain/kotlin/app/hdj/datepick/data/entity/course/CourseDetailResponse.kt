package app.hdj.datepick.data.entity.course

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CourseDetailResponse(
    @SerialName("id") val id: Long,
    @SerialName("user_id") val user_id: Long,
    @SerialName("title") val title: String,
    @SerialName("region") val region: String,
    @SerialName("thumbnail_url") val thumbnailUrl: String,
    @SerialName("import_count") val importCount: Long,
    @SerialName("pick_count") val pickCount: Long,
    @SerialName("is_picked") val isPicked: Boolean,
    @SerialName("places") val places: List<CoursePlacesResponse>,
    @SerialName("expected_at") val expectedAt: String,
)