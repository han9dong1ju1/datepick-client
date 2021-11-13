package app.hdj.datepick.domain.usecase.user.params

import app.hdj.datepick.domain.model.user.UserGender
import io.ktor.utils.io.core.*


data class UserProfileRequestParams(
    val nickname: String,
    val gender: UserGender,
    val image: Input? = null,
)
