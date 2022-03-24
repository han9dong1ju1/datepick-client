package app.hdj.datepick.domain.model.course

import app.hdj.datepick.utils.Parcelable
import app.hdj.datepick.utils.Parcelize

@Parcelize
data class CourseTag(
    val id: Long,
    val name: String
) : Parcelable