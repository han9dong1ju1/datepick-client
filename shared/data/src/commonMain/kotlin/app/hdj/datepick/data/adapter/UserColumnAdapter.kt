package app.hdj.datepick.data.adapter

import app.hdj.datepick.data.entity.user.UserResponse
import app.hdj.datepick.domain.model.user.User
import com.squareup.sqldelight.ColumnAdapter
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object UserColumnAdapter  : ColumnAdapter<UserResponse, String> {

    override fun decode(databaseValue: String): UserResponse {
        return Json.decodeFromString(databaseValue)
    }

    override fun encode(value: UserResponse): String {
        return Json.encodeToString(value)
    }

}