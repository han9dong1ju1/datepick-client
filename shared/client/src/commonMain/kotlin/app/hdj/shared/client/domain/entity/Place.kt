package app.hdj.shared.client.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class Place(
    override val id: String,
    val name: String,
    val photoUrl : String? = null,
    var cacheExpireAt: Long = 0
) : Id