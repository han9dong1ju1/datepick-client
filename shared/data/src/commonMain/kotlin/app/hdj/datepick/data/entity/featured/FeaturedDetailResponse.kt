package app.hdj.datepick.data.entity.featured

import app.hdj.datepick.data.entity.CourseResponse
import app.hdj.datepick.domain.model.featured.FeaturedDetail
import kotlinx.serialization.Serializable

@Serializable
data class FeaturedDetailResponse(
    override val meta: FeaturedResponse,
    override val content: String,
    override val courses: List<CourseResponse>
) : FeaturedDetail