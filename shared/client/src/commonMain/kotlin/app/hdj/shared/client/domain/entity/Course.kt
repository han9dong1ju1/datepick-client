package app.hdj.shared.client.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class Course(
    override val id: String,
    val places: List<PlaceWithMemo>,
) : Id {

    companion object {



    }

}

@Serializable
data class PlaceWithMemo(
    val memo: String,
    val place: Place
)
