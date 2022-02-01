package app.hdj.datepick.data.adapter

import app.hdj.datepick.data.entity.user.UserData
import app.hdj.datepick.domain.model.user.User
import com.squareup.sqldelight.ColumnAdapter
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object UserColumnAdapter  : ColumnAdapter<UserData, String> {

    override fun decode(databaseValue: String): UserData {
        return Json.decodeFromString(databaseValue)
    }

    override fun encode(value: UserData): String {
        return Json.encodeToString(value)
    }

}