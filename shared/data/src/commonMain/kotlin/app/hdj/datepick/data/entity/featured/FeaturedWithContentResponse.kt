package app.hdj.datepick.data.entity.featured

import app.hdj.datepick.domain.model.featured.Featured
import app.hdj.datepick.domain.model.featured.FeaturedWithContent
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FeaturedWithContentResponse(
    @SerialName("id") override val id : Long = 0,
    @SerialName("title") override val title : String,
    @SerialName("content") override val content : String,
    @SerialName("description") override val description : String,
    @SerialName("photo_url") override val photoUrl : String
) : FeaturedWithContent