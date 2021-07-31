package app.hdj.shared.client.domain.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.random.Random

@Serializable
data class CourseDetail(
    override val id: String,
    @SerialName("metadata") val metadata: CourseMetadata,
    @SerialName("placesWithMemo") val placesWithMemo: List<PlaceWithMemo>,
) : Id

@Serializable
data class PlaceWithMemo(
    val memo : String? = null,
    val place: Place
)