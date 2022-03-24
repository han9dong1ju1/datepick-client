package app.hdj.datepick.data.entity.featured

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FeaturedResponse(
    @SerialName("id") val id : Long,
    @SerialName("title") val title : String,
    @SerialName("subtitle") val subtitle : String,
    @SerialName("content") val content : String,
    @SerialName("image_url") val imageUrl : String,
    @SerialName("is_pinned") val isPinned: Boolean
)