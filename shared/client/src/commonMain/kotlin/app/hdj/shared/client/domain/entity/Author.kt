package app.hdj.shared.client.domain.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Author(
    @SerialName("id") override val id : String,
    @SerialName("name") val name : String,
    @SerialName("profile_photo_url") val profilePhotoUrl : String
) : Id