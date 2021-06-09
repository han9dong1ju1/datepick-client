package app.hdj.places.client

import android.content.Context
import app.hdj.places.model.Place
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place.Field
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import kotlinx.coroutines.tasks.await

actual class PlacesClient(context: Context) {

    private val android = Places.createClient(context)

    actual suspend fun fetchPlace(placeId: String): Place = android.fetchPlace(
        FetchPlaceRequest.newInstance(placeId, Field.values().toList())
    ).await().place.run {
        Place(
            name,
            rating
        )
    }

}