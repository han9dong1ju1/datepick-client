package app.hdj.places.client

import app.hdj.places.model.Place

expect class PlacesClient {

    suspend fun fetchPlace(placeId: String) : Place

}