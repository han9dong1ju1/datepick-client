package app.hdj.datepick.data.entity.featured

import app.hdj.datepick.domain.model.featured.Featured
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FeaturedResponse(
    @SerialName("id") override val id : Int,
    @SerialName("title") override val title : String,
    @SerialName("description") override val description : String,
    @SerialName("photoUrl") override val photoUrl : String
) : Featured