package app.hdj.datepick.data.request.user

import kotlinx.serialization.Serializable

@Serializable
data class UserRegisterRequest(
    val provider : String,
    val token : String
)