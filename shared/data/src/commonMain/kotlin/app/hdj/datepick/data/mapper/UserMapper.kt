package app.hdj.datepick.data.mapper

import app.hdj.datepick.UserTable
import app.hdj.datepick.data.entity.UserResponse
import app.hdj.datepick.domain.model.user.User
import kotlinx.datetime.Clock

object UserMapper : Mapper<UserTable, UserResponse> {

    override fun map(table: UserTable): UserResponse = with(table) {
        UserResponse(id, nickname, profileImageUrl)
    }

    override fun map(response: UserResponse): UserTable = with(response) {
        UserTable(id, nickname, profileImageUrl, Clock.System.now().epochSeconds)
    }

}