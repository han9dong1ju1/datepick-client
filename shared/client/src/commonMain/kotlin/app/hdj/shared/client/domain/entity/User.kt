package app.hdj.shared.client.domain.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("id") override val id: String,
    @SerialName("name") val name: String? = null,
    @SerialName("profile_photo_url") val profilePhotoUrl: String? = null,
    @SerialName("email") val email : String? = null
) : Id

fun fakeUser() = User("1", "Harry", "...")