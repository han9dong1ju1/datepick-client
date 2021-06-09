package app.hdj.places.client

import app.hdj.places.model.Place
import cocoapods.GooglePlaces.GMSPlace
import cocoapods.GooglePlaces.GMSPlaceFieldAll
import cocoapods.GooglePlaces.GMSPlacesClient
import kotlinx.coroutines.suspendCancellableCoroutine
import platform.Foundation.NSError
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

actual class PlacesClient {

    private val ios = GMSPlacesClient.sharedClient()

    actual suspend fun fetchPlace(placeId: String) = suspendCancellableCoroutine<Place> { cont ->
        if (!cont.isActive) return@suspendCancellableCoroutine

        ios.fetchPlaceFromPlaceID(
            placeId,
            GMSPlaceFieldAll,
            null
        ) { gmsPlace: GMSPlace?, nsError: NSError? ->

            if (gmsPlace == null || nsError != null) {
                cont.resumeWithException(Exception("${nsError?.code} : ${nsError?.localizedDescription}"))
            } else {
                cont.resume(
                    Place(
                        gmsPlace.name,
                        gmsPlace.rating.toDouble()
                    )
                )
            }

        }
    }

}