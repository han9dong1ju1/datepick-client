package app.hdj.datepick.data.mapper

import app.hdj.datepick.PlaceEntity
import app.hdj.datepick.domain.model.place.Place
import kotlinx.datetime.Clock

object PlaceMapper : Mapper<PlaceEntity, Place> {

    override fun PlaceEntity.asDomain() = object : Place {
        override val id: Long = this@asDomain.id
        override val kakaoId: Long = this@asDomain.kakaoId
        override val name: String = this@asDomain.name
    }

    override fun Place.asTable(): PlaceEntity =
        PlaceEntity(id, kakaoId, name, Clock.System.now().epochSeconds)

}