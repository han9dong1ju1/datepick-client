package app.hdj.shared.client.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class Review(
    override val id: String,
    val text: String,
    val author: Author?,
    val rating: Float,
    val timestamp: String,
) : Id