package app.hdj.shared.client.domain.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlaceCategory(
    @SerialName("name") val name: String,
    @SerialName("subtype") val subtype: String,
    @SerialName("type") val type: String
)