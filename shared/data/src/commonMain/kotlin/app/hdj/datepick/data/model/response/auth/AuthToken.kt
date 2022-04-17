package app.hdj.datepick.data.model.response.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthToken(
    @SerialName("access_token") val accessToken : String,
    @SerialName("refresh_token") val refreshToken : String,
    @SerialName("expires_in") val expiredIn : Long,
)