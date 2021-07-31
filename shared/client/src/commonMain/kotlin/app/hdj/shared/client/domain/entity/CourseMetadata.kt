package app.hdj.shared.client.domain.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.random.Random

@Serializable
data class CourseMetadata(
    override val id: String,
    @SerialName("name") val name: String,
    @SerialName("is_picked") val isPicked : Boolean,
    @SerialName("option") val optionAsString : List<String>,
    @SerialName("photo_url") val photoUrl: String,
    @SerialName("author") val author: Author
) : Id {

    val option get() = optionAsString.map {

    }

}

enum class CourseOption {
    LUNCH_ONLY,
    DINNER_ONLY,
    NO_DINING,



}