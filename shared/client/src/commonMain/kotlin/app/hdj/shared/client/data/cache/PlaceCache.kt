package app.hdj.shared.client.data.cache

import app.hdj.shared.client.domain.entity.Place

open class PlaceCache : Cache<Place> {

    override suspend fun get(id: String): Place? {
        TODO("Not yet implemented")
    }

    override suspend fun cache(data: Place) {

    }

}