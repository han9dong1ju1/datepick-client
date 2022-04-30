package app.hdj.datepick.data.model.response.course

import app.hdj.datepick.data.model.response.user.PublicUserResponse
import app.hdj.datepick.data.model.response.user.UserResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CourseResponse(
    @SerialName("id") val id: Long,
    @SerialName("title") val title: String,
    @SerialName("meet_at") val meetAt: String? = null,
    @SerialName("created_at") val createdAt: String? = null,
    @SerialName("updated_at") val updatedAt: String? = null,
    @SerialName("image_url") val imageUrl: String?,
    @SerialName("is_private") val isPrivate: Boolean,
    @SerialName("view_count") val viewCount: Long,
    @SerialName("pick_count") val pickCount: Long,
    @SerialName("is_picked") val isPicked: Boolean,
    @SerialName("user") val user: PublicUserResponse,
    @SerialName("tags") val tags: List<CourseTagResponse>
)

@Serializable
data class CourseTagResponse(
    @SerialName("id") val id: Long,
    @SerialName("name") val name: String
)