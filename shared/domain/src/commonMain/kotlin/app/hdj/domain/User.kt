package app.hdj.domain

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Int,
    val name: String
)