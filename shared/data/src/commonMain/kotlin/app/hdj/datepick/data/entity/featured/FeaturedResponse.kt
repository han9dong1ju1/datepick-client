package app.hdj.datepick.data.entity.featured

import app.hdj.datepick.domain.model.featured.Featured
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FeaturedResponse(
    @SerialName("id") override val id : Long,
    @SerialName("title") override val title : String,
    @SerialName("subtitle") override val subtitle : String,
    @SerialName("content") override val content : String,
    @SerialName("image_url") override val imageUrl : String,
    @SerialName("is_pinned") override val isPinned: Boolean
) : Featured