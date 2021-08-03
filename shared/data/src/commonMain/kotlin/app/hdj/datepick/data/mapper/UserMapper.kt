package app.hdj.datepick.data.mapper

import app.hdj.datepick.UserTable
import app.hdj.datepick.data.entity.UserResponse
import app.hdj.datepick.domain.model.user.User
import kotlinx.datetime.Clock

object UserMapper : Mapper<UserTable, User> {

    override fun map(table: UserTable) = object : User {
        override val id: String = table.id
        override val nickname: String = table.nickname
        override val profileImageUrl: String? = table.profileImageUrl
    }

    override fun map(model: User): UserTable = with(model) {
        UserTable(id, nickname, profileImageUrl, Clock.System.now().epochSeconds)
    }

}