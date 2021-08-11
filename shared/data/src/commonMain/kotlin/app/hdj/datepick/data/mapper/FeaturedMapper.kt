package app.hdj.datepick.data.mapper

import app.hdj.datepick.FeaturedEntity
import app.hdj.datepick.domain.model.featured.Featured
import kotlinx.datetime.Clock

object FeaturedMapper : Mapper<FeaturedEntity, Featured> {

    override fun FeaturedEntity.asDomain() = object : Featured {
        override val id: Int = this@asDomain.id.toInt()
        override val title: String = this@asDomain.title
        override val description: String = this@asDomain.description
        override val photoUrl: String = this@asDomain.photoUrl
    }

    override fun Featured.asTable(): FeaturedEntity =
        FeaturedEntity(id.toString(), title, description, photoUrl, Clock.System.now().epochSeconds)

}