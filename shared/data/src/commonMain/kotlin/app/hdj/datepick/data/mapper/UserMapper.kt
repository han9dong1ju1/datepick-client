package app.hdj.datepick.data.mapper

import app.hdj.datepick.UserTable
import app.hdj.datepick.data.entity.UserResponse
import app.hdj.datepick.domain.model.user.User
import kotlinx.datetime.Clock

object UserMapper : Mapper<UserTable, User> {

    override fun UserTable.asDomain() = object : User {
        override val id: String = this@asDomain.id
        override val isMe: Boolean = this@asDomain.isMe
        override val nickname: String = this@asDomain.nickname
        override val profileImageUrl: String? = this@asDomain.profileImageUrl
    }

    override fun User.asTable(): UserTable {
        return UserTable(
            id,
            nickname,
            profileImageUrl,
            isMe,
            Clock.System.now().epochSeconds
        )
    }

}