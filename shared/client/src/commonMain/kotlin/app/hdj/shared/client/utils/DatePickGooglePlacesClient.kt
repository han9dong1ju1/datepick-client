package app.hdj.shared.client.utils

data class Origin(
    val lat: Double,
    val lon: Double
)

data class PlacesAutocompletePredictionsQuery(
    val query: String?,
    val origin: Origin
)

data class PlacesAutocompletePredictionsResponse(
    val placeId: String,
    val name: String,
    val description: String,
    val placeTypes : List<String>,
    val distanceMeters : Int?
) {

    val distanceAsString : String? get() {
        if (distanceMeters == null) return null
        return if (distanceMeters >= 1000) "${(distanceMeters / 1000f).round(1)}km"
        else "${distanceMeters}m"
    }

}

expect class DatePickGooglePlacesClient {

    suspend fun fetchPlace(placeId: String)

    suspend fun findAutocompletePredictionsFromQuery(query: PlacesAutocompletePredictionsQuery): List<PlacesAutocompletePredictionsResponse>

}