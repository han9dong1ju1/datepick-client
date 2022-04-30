package app.hdj.datepick.domain.model.place

import app.hdj.datepick.utils.Parcelable
import app.hdj.datepick.utils.Parcelize

@Parcelize
data class Place(
    val id: Long,
    val kakaoId: String?,
    val name: String,
    val categories: List<Category>,
    val address: String,
    val latitude: Double,
    val longitude: Double,
    val rating: Double?,
    val isPicked: Boolean,
    val imageUrl: String?
) : Parcelable {

    @Parcelize
    data class Category(val id : Long, val name: String) : Parcelable

}