package app.hdj.places.client

import app.hdj.places.model.Place
import app.hdj.places.model.PlaceAutoCompletePredictions
import cocoapods.GooglePlaces.*
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

    actual suspend fun searchPlaces(query : String) =
        suspendCancellableCoroutine<List<PlaceAutoCompletePredictions>> { cont ->
            val filter = GMSAutocompleteFilter()
            ios.findAutocompletePredictionsFromQuery(
                "",
                filter,
                null
            ) { autocompletePrediction, _ ->
                autocompletePrediction?.map {
                    val gmsAutoCompletePrediction = it as GMSAutocompletePrediction
                    PlaceAutoCompletePredictions(
                        gmsAutoCompletePrediction.attributedPrimaryText.string,
                        gmsAutoCompletePrediction.attributedSecondaryText?.string.orEmpty(),
                        gmsAutoCompletePrediction.placeID,
                        gmsAutoCompletePrediction.types.mapNotNull { iosAutoCompletePrediction ->
                            PlaceAutoCompletePredictions.Types.values().find {
                                iosAutoCompletePrediction.toString().uppercase() == it.name
                            }
                        }
                    )
                }
            }
        }

}