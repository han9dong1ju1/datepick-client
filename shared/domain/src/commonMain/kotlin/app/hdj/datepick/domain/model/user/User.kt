package app.hdj.datepick.domain.model.user

import app.hdj.datepick.utils.Parcelable
import app.hdj.datepick.utils.Parcelize

enum class UserGender {
    M, F
}

@Parcelize
data class User(
    val id: Long,
    val nickname: String,
    val imageUrl: String?,
    val gender: UserGender?,
) : Parcelable