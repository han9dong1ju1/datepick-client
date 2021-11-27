package app.hdj.datepick.data.entity.featured

import app.hdj.datepick.domain.model.featured.FeaturedDetail
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FeaturedDetailResponse(
    @SerialName("course_metas") override val courses: List<FeaturedCourseMetasResponse>,
    @SerialName("featured_detail") override val featured: FeaturedWithContentResponse
) : FeaturedDetail