package app.hdj.shared.client.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class Review(
    override val id: String,
    val userId: String,
    val profileUrl: String,
    val nickname: String,
    val createdAt: String,
    val rating: Float,
    val comment: String? = null,
    val photo: List<String>? = null,
    val categories: List<String>? = null
) : Id