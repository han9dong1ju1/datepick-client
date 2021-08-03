package app.hdj.datepick.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class CourseMetadataResponse(
    val id : String,
    val name : String
)