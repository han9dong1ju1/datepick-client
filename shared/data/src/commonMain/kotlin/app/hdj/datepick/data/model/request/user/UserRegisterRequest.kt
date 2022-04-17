package app.hdj.datepick.data.model.request.user

import kotlinx.serialization.Serializable

@Serializable
data class UserRegisterRequest(
    val token : String
)