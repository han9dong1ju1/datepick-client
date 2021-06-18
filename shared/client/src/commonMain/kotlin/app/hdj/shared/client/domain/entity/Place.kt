package app.hdj.shared.client.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class Place(
    val name : String,
    val rating : Double
)