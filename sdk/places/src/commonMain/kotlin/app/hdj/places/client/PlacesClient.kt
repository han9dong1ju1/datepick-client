package app.hdj.places.client

import app.hdj.places.model.Place
import app.hdj.places.model.PlaceAutoCompletePredictions

expect class PlacesClient {

    suspend fun searchPlaces(query : String) : List<PlaceAutoCompletePredictions>

    suspend fun fetchPlace(placeId: String) : Place

}