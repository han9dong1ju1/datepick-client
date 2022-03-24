package app.hdj.datepick.data.mapper

import app.hdj.datepick.FeaturedEntity
import app.hdj.datepick.data.entity.featured.FeaturedResponse
import app.hdj.datepick.domain.model.featured.Featured
import kotlinx.datetime.Clock

object FeaturedMapper : Mapper<FeaturedEntity, FeaturedResponse, Featured> {

    override fun FeaturedEntity.tableToDomain() = Featured(
        id = id,
        title = title,
        subtitle = subtitle,
        imageUrl = imageUrl,
        content = content,
        isPinned = isPinned,
    )

    override fun Featured.domainToTable() =
        FeaturedEntity(id, title, subtitle, content, imageUrl, isPinned, Clock.System.now().epochSeconds)

    override fun FeaturedResponse.dataToDomain() = Featured(
        id = id,
        title = title,
        subtitle = subtitle,
        imageUrl = imageUrl,
        content = content,
        isPinned = isPinned,
    )

    override fun FeaturedResponse.dataToTable() =
        FeaturedEntity(id, title, subtitle, content, imageUrl, isPinned, Clock.System.now().epochSeconds)

}