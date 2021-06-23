package app.hdj.shared.client.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class Place(
    val id : String,
    val name: String,
    var cacheExpireAt: Long = 0
)