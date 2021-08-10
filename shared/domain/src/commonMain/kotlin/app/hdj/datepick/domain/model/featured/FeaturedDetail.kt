package app.hdj.datepick.domain.model.featured

import app.hdj.datepick.domain.model.course.Course

interface FeaturedDetail {

    val meta: Featured
    val content: String
    val courses: List<Course>

}