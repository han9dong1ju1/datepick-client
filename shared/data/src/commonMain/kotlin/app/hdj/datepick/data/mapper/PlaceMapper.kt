package app.hdj.datepick.data.mapper

import app.hdj.datepick.PlaceEntity
import app.hdj.datepick.domain.model.place.Place
import kotlinx.datetime.Clock

object PlaceMapper : Mapper<PlaceEntity, Place> {

    override fun PlaceEntity.asDomain() = object : Place {
        override val id: Long = this@asDomain.id
        override val kakaoId: Long = this@asDomain.kakaoId
        override val name: String = this@asDomain.name
        override val address: String = this@asDomain.address
        override val latitude: Double = this@asDomain.latitude
        override val longitude: Double = this@asDomain.longitude
        override val rating: Double = this@asDomain.rating
        override val isPicked: Boolean = this@asDomain.isPicked
        override val photos: List<String> = this@asDomain.photos
    }

    override fun Place.asTable(): PlaceEntity =
        PlaceEntity(id, kakaoId, name, address, isPicked, latitude, longitude, rating, photos, Clock.System.now().epochSeconds)

}