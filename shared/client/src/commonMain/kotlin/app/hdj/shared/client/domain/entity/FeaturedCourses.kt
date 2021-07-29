package app.hdj.shared.client.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class FeaturedCourses(
    override val id : String,
    val title : String,
    val photoUrl : String,
    val description : String,
    val courses : List<Course>
) : Id