package app.hdj.datepick.domain.model.featured

import app.hdj.datepick.domain.model.course.Course
import app.hdj.datepick.domain.model.course.FeatureCourseMatas

interface FeaturedDetail {

    val featured : FeaturedWithContent
    val courses: List<FeatureCourseMatas>

}