package app.hdj.shared.client.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class User(
    override val id: String,
    val name: String? = null,
    val profileUrl: String? = null,
) : Id

fun fakeUser() = User("1", "Harry", "...")

fun fakeNoInfoUser() = User("2")