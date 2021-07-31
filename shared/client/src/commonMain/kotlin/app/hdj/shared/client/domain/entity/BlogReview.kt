package app.hdj.shared.client.domain.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BlogReview(
    @SerialName("title") val title : String,
    @SerialName("description") val description : String,
    @SerialName("url") val url : String,
    @SerialName("thumbnail_url") val thumbnailUrl : String
)