package app.hdj.shared.client.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class NotificationSettings(
    val all : Boolean = true,
    val courseLike : Boolean = true,
    val marketing : Boolean = true
)