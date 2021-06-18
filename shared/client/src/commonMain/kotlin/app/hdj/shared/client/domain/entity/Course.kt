package app.hdj.shared.client.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class Course(
    val places: List<PlaceWithMemo>
)

@Serializable
data class PlaceWithMemo(
    val memo : String,
    val place: Place
)
