package app.hdj.datepick.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class CourseResponse(
    val id : String,
    val name : String
)