package app.hdj.datepick.domain.model.user

enum class UserGender {
    U, M, F
}

interface User {

    val id: Long
    val nickname: String
    val uid : String
    val gender : UserGender
    val profileImage: String?

    val isMe : Boolean
}