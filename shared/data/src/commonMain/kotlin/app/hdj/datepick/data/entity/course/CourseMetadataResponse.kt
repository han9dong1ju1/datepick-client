package app.hdj.datepick.data.entity.course

import kotlinx.serialization.Serializable

@Serializable
data class CourseMetadataResponse(
    val id : String,
    val name : String
)