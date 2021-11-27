package app.hdj.datepick.data.entity.featured

import app.hdj.datepick.data.entity.CourseResponse
import app.hdj.datepick.domain.model.course.FeatureCourseMatas
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FeaturedCourseMetasResponse(
    @SerialName("order") override val order : Int,
    @SerialName("course_meta") override val courseMeta : CourseResponse
) : FeatureCourseMatas