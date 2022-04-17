package app.hdj.datepick.data.model.request.user

import app.hdj.datepick.domain.model.user.UserGender
import io.ktor.utils.io.core.*

data class UserProfileRequest(
    val nickname: String,
    val gender: UserGender?,
    val image: Input?,
)