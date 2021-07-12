package app.hdj.shared.client.utils

import cocoapods.GooglePlaces.*
import platform.Foundation.NSError

actual class DatePickGooglePlacesClient {

    private val client = GMSPlacesClient.sharedClient()

    private var availableField : GMSPlaceField? = null

    fun setAvailableFields(gmsPlaceField: GMSPlaceField) {
        this.availableField = gmsPlaceField
    }

    actual suspend fun fetchPlace(placeId: String) {
        val sessionToken = GMSAutocompleteSessionToken.new()
        client.fetchPlaceFromPlaceID(
            placeId,
            availableField!!,
            sessionToken
        ) { gmsPlace: GMSPlace?, nsError: NSError? ->

        }
    }

    actual suspend fun findAutocompletePredictionsFromQuery(query: PlacesAutocompletePredictionsQuery): List<PlacesAutocompletePredictionsResponse> {
        TODO("Not yet implemented")
    }

}