package app.hdj.places.client

import android.content.Context
import app.hdj.places.model.Place
import app.hdj.places.model.PlaceAutoCompletePredictions
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place.Field
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.ktx.api.net.*
import kotlinx.coroutines.tasks.await

actual class PlacesClient(context: Context) {

    private val android = Places.createClient(context)

    actual suspend fun searchPlaces(query: String): List<PlaceAutoCompletePredictions> {
        val request = findAutocompletePredictionsRequest {

        }
        val predictions = android.awaitFindAutocompletePredictions(request)
        return predictions.autocompletePredictions.map { autocompletePrediction ->
            PlaceAutoCompletePredictions(
                autocompletePrediction.getPrimaryText(null).toString(),
                autocompletePrediction.getSecondaryText(null).toString(),
                autocompletePrediction.placeId,
                autocompletePrediction.placeTypes.mapNotNull { androidPlaceTypes ->
                    PlaceAutoCompletePredictions.Types.values()
                        .find { it.name == androidPlaceTypes.name }
                }
            )
        }
    }

    actual suspend fun fetchPlace(placeId: String): Place = android.awaitFetchPlace(
        FetchPlaceRequest.newInstance(placeId, Field.values().toList())
    ).run {
        Place(
            place.name,
            place.rating
        )
    }

}