package app.hdj.datepick.data.request.user

import app.hdj.datepick.domain.model.user.UserGender
import io.ktor.utils.io.core.*
import kotlinx.serialization.Serializable

@Serializable
data class UserProfileRequest(
    val nickname: String,
    val gender : UserGender,
    val image: Input?,
)