package app.hdj.shared.client.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String,
    val name: String
)