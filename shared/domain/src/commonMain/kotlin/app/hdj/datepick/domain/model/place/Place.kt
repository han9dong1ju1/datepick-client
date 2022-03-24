package app.hdj.datepick.domain.model.place

import app.hdj.datepick.utils.Parcelable
import app.hdj.datepick.utils.Parcelize

@Parcelize
data class Place(
    val id: Long,
    val kakaoId: Long,
    val name: String,
    val category: Category,
    val address: String,
    val latitude: Double,
    val longitude: Double,
    val rating: Double,
    val isPicked: Boolean,
    val photo: String?
) : Parcelable {

    @Parcelize
    data class Category(
        val category: String,
        val type: String,
        val subtype: String
    ) : Parcelable {
        val typeAsString get() = """$category ㆍ $type ㆍ $subtype"""
    }
}