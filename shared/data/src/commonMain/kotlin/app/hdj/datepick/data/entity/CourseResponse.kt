package app.hdj.datepick.data.entity

import app.hdj.datepick.domain.model.course.Course
import kotlinx.serialization.Serializable

@Serializable
data class CourseResponse(
    override val id : String,
    override val name : String
) : Course