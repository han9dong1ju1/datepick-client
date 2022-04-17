package app.hdj.datepick.data.mapper

import app.hdj.datepick.data.model.response.place.PlaceResponse
import app.hdj.datepick.domain.model.place.Place

object PlaceMapper : Mapper<PlaceResponse, Place> {
    override fun PlaceResponse.dataToDomain(): Place {
        return Place(
            id = id,
            kakaoId = kakaoId,
            name = name,
            categories = categories.map { Place.Category(it.id, it.name) },
            address = address,
            latitude = latitude,
            longitude = longitude,
            rating = rating,
            isPicked = isPicked,
            imageUrl = imageUrl
        )
    }
}