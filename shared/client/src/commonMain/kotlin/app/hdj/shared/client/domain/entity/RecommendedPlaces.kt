package app.hdj.shared.client.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class RecommendedPlaces(
    val title: String,
    val places: List<Place>
)