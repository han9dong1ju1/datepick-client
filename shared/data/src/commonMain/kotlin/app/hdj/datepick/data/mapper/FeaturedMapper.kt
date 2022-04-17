package app.hdj.datepick.data.mapper

import app.hdj.datepick.data.model.response.featured.FeaturedResponse
import app.hdj.datepick.domain.model.featured.Featured

object FeaturedMapper : Mapper<FeaturedResponse, Featured> {
    override fun FeaturedResponse.asDomain(): Featured {
        return Featured(
            id = id,
            title = title,
            subtitle = subtitle,
            content = content,
            imageUrl = imageUrl,
            isPinned = isPinned
        )
    }
}