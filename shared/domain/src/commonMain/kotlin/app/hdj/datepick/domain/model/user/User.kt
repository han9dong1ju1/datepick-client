package app.hdj.datepick.domain.model.user

enum class UserGender {
    M, F
}

interface User {

    val id: Long
    val nickname: String
    val gender: UserGender?
    val imageUrl: String?

}