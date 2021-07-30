package app.hdj.shared.client.domain.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Featured(
    @SerialName("id") override val id : String,
    @SerialName("title") val title : String,
    @SerialName("photo_url") val photoUrl : String,
    @SerialName("description") val description : String
) : Id