package app.hdj.datepick.data.model.response.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthTokenResponse(
    @SerialName("access_token") val accessToken : String,
    @SerialName("refresh_token") val refreshToken : String,
    @SerialName("expire_in") val expireIn : Long,
)