package app.hdj.datepick.domain.model.user

interface User {
    val id: Long
    val nickname: String
    val isMe : Boolean
    val profileImageUrl: String?
}