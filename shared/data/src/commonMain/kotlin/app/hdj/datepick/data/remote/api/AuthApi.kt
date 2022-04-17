package app.hdj.datepick.data.remote.api

import app.hdj.datepick.data.model.request.auth.AuthRefreshTokenRequest
import app.hdj.datepick.data.model.response.auth.AuthToken
import app.hdj.datepick.data.remote.Api
import app.hdj.datepick.data.remote.ApiResponse
import app.hdj.datepick.data.remote.get
import app.hdj.datepick.data.remote.post
import app.hdj.datepick.utils.di.Inject
import app.hdj.datepick.utils.di.Singleton
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.delay

fun fakeAuthApi(): AuthApi = object : AuthApi {
    override val client: HttpClient
        get() = TODO("Not yet implemented")

    override suspend fun signIn(token: String, provider: String): ApiResponse<AuthToken> {
        delay(1000)
        return ApiResponse(
            data = AuthToken(
                accessToken = "token",
                refreshToken = "refreshToken",
                expiredIn = 120
            ),
            error = null,
            message = null
        )
    }

    override suspend fun unregister(reason: String): ApiResponse<Unit> {
        delay(1000)
        return ApiResponse(
            data = Unit,
            error = null,
            message = null
        )
    }

    override suspend fun refreshToken(tokenRequest: AuthRefreshTokenRequest): ApiResponse<AuthToken> {
        delay(1000)
        return ApiResponse(
            data = AuthToken(
                accessToken = "token",
                refreshToken = "refreshToken",
                expiredIn = 120
            ),
            error = null,
            message = null
        )
    }

}

interface AuthApi : Api {

    override val basePath: String get() = "/v1/auth/"

    suspend fun signIn(token: String, provider: String): ApiResponse<AuthToken>

    suspend fun unregister(reason: String): ApiResponse<Unit>

    suspend fun refreshToken(tokenRequest: AuthRefreshTokenRequest): ApiResponse<AuthToken>

}

@Singleton
class AuthApiImp @Inject constructor(override val client: HttpClient) : AuthApi {

    override suspend fun signIn(token: String, provider: String): ApiResponse<AuthToken> = post("signin") {
        parameter("token", token)
        parameter("provider", provider)
    }

    override suspend fun unregister(reason: String): ApiResponse<Unit> = post("unregister") {
        parameter("reason", reason)
    }

    override suspend fun refreshToken(tokenRequest: AuthRefreshTokenRequest): ApiResponse<AuthToken> = post("refresh") {
        setBody(tokenRequest)
    }

}