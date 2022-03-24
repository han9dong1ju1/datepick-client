package app.hdj.datepick.domain.model.featured

import app.hdj.datepick.utils.Parcelable
import app.hdj.datepick.utils.Parcelize

@Parcelize
data class Featured(
    val id: Long,
    val title: String,
    val subtitle: String,
    val content: String,
    val imageUrl: String,
    val isPinned: Boolean
) : Parcelable