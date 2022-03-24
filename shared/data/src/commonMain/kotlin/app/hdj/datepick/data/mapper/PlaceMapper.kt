package app.hdj.datepick.data.mapper

import app.hdj.datepick.PlaceEntity
import app.hdj.datepick.data.entity.place.PlaceResponse
import app.hdj.datepick.domain.model.place.Place
import kotlinx.datetime.Clock

object PlaceMapper : Mapper<PlaceEntity, PlaceResponse, Place> {

    override fun PlaceEntity.tableToDomain() = Place(
        category = Place.Category(category = category, type = type, subtype = subtype),
        id = id,
        kakaoId = kakaoId,
        name = name,
        address = address,
        latitude = latitude,
        longitude = longitude,
        rating = rating,
        isPicked = isPicked,
        photo = photo
    )

    override fun Place.domainToTable() = PlaceEntity(
        id,
        kakaoId,
        name,
        address,
        category.category,
        category.type,
        category.subtype,
        isPicked,
        latitude,
        longitude,
        rating,
        photo,
        Clock.System.now().epochSeconds
    )

    override fun PlaceResponse.dataToDomain() = Place(
        category = Place.Category(category = category.category, type = category.type, subtype = category.subtype),
        id = id,
        kakaoId = kakaoId,
        name = name,
        address = address,
        latitude = latitude,
        longitude = longitude,
        rating = rating,
        isPicked = isPicked,
        photo = photo
    )

    override fun PlaceResponse.dataToTable() = PlaceEntity(
        id,
        kakaoId,
        name,
        address,
        category.category,
        category.type,
        category.subtype,
        isPicked,
        latitude,
        longitude,
        rating,
        photo,
        Clock.System.now().epochSeconds
    )

}