package app.hdj.datepick.data.mapper

import app.hdj.datepick.FeaturedEntity
import app.hdj.datepick.domain.model.featured.Featured
import kotlinx.datetime.Clock
import kotlin.random.Random

object FeaturedMapper : Mapper<FeaturedEntity, Featured> {

    override fun FeaturedEntity.asDomain() = object : Featured {
        override val id: Long = this@asDomain.id
        override val title: String = this@asDomain.title
        override val subtitle: String = this@asDomain.subtitle
        override val imageUrl: String = this@asDomain.imageUrl
        override val content: String = this@asDomain.content
        override val isPinned: Boolean = this@asDomain.isPinned
    }

    override fun Featured.asTable(): FeaturedEntity =
        FeaturedEntity(id, title, subtitle, content, imageUrl, isPinned, Clock.System.now().epochSeconds)

}