package app.hdj.datepick.data.entity.featured

import app.hdj.datepick.domain.model.featured.Featured
import kotlinx.serialization.Serializable

@Serializable
data class FeaturedResponse(
    override val id : String,
    override val title : String,
    override val description : String,
    override val photoUrl : String
) : Featured