package app.hdj.datepick.data.model.request.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthRefreshTokenRequest(
    @SerialName("refresh_token") val refreshToken: String
)