package app.hdj.datepick.data.mapper

import app.hdj.datepick.UserTable
import app.hdj.datepick.data.entity.UserResponse
import app.hdj.datepick.domain.model.User
import kotlinx.datetime.Clock

object UserMapper : Mapper<UserTable, UserResponse, User> {

    override fun map(table: UserTable): UserResponse = with(table) {
        UserResponse(id, firebaseId, nickname, profileImageUrl)
    }

    override fun map(response: UserResponse): UserTable = with(response) {
        UserTable(id, firebaseId, nickname, profileImageUrl, Clock.System.now().epochSeconds)
    }

    override fun transfer(response: UserResponse): User = with(response) {
        User(id, nickname, profileImageUrl)
    }

    override fun transfer(table: UserTable): User = with(table) {
        User(id, nickname, profileImageUrl)
    }

}