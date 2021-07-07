package app.hdj.shared.client.utils

import android.content.Context
import android.graphics.Typeface
import android.text.style.CharacterStyle
import android.text.style.StyleSpan
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.model.RectangularBounds
import com.google.android.libraries.places.api.model.TypeFilter
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.android.libraries.places.ktx.api.net.awaitFetchPlace
import com.google.android.libraries.places.ktx.api.net.awaitFindAutocompletePredictions
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.cos

fun getCoordinate(lat0: Double, lng0: Double, dy: Long, dx: Long): LatLng {
    val lat = lat0 + 180 / Math.PI * (dy / 6378137)
    val lng = lng0 + 180 / Math.PI * (dx / 6378137) / cos(lat0)
    return LatLng(lat, lng)
}

@Singleton
actual class DatePickGooglePlacesClient @Inject constructor(@ApplicationContext context: Context) {

    private val availableFields = listOf(
        Place.Field.ID,
        Place.Field.NAME,
        Place.Field.ADDRESS,
        Place.Field.LAT_LNG,
        Place.Field.BUSINESS_STATUS,
        Place.Field.PHOTO_METADATAS,
        Place.Field.TYPES
    )

    private val client = Places.createClient(context)

    actual suspend fun fetchPlace(placeId: String) {
        val response = client.awaitFetchPlace(
            FetchPlaceRequest.newInstance(placeId, availableFields)
        )
        response.place
    }

    actual suspend fun findAutocompletePredictionsFromQuery(
        query: PlacesAutocompletePredictionsQuery
    ): List<PlacesAutocompletePredictionsResponse> {

        val token = AutocompleteSessionToken.newInstance()

        val targetLocation = LatLng(query.origin.lat, query.origin.lon)

        val bounds = RectangularBounds.newInstance(
            getCoordinate(targetLocation.latitude, targetLocation.longitude, -10_000, -10_000),
            getCoordinate(targetLocation.latitude, targetLocation.longitude, 10_000, 10_000)
        )

        val request = FindAutocompletePredictionsRequest.builder()
            .setCountry(Locale.KOREA.country)
            .setQuery(query.query)
            .setTypeFilter(TypeFilter.ESTABLISHMENT)
            .setOrigin(LatLng(query.origin.lat, query.origin.lon))
            .setLocationBias(bounds)
            .setSessionToken(token)

        val predictions = client.awaitFindAutocompletePredictions(request.build())
        return predictions.autocompletePredictions.map { autocompletePrediction ->
            with(autocompletePrediction) {
                PlacesAutocompletePredictionsResponse(
                    placeId,
                    getPrimaryText(StyleSpan(Typeface.BOLD)).toString(),
                    getSecondaryText(null).toString(),
                    placeTypes.map { it.name },
                    distanceMeters
                )
            }
        }
    }

}