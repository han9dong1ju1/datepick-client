package app.hdj.datepick.data.remote.client

import app.hdj.datepick.data.model.request.auth.AuthRefreshTokenRequest
import app.hdj.datepick.data.model.response.auth.AuthTokenResponse
import app.hdj.datepick.data.utils.AuthTokenManager
import app.hdj.datepick.utils.AppInfo
import app.hdj.datepick.utils.PlatformLogger
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.cache.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.*
import kotlinx.serialization.json.Json

private const val URL = "api-dev.datepick.app"

private val responseKotlinSerializationJson =
    Json {
        ignoreUnknownKeys = true
        allowSpecialFloatingPointValues = true
        useArrayPolymorphism = true
        prettyPrint = true
    }

@Suppress("FunctionName")
fun <T : HttpClientEngineConfig> DatepickApiHttpClient(
    engineFactory: HttpClientEngineFactory<T>,
    authTokenManager: AuthTokenManager,
    appInfo: AppInfo,
    block: HttpClientConfig<T>.() -> Unit = {}
) = HttpClient(engineFactory) {

    developmentMode = appInfo.debug
    expectSuccess = true

    install(ContentNegotiation) {
        register(
            contentType = ContentType.Application.Json,
            converter = KotlinxSerializationConverter(responseKotlinSerializationJson)
        )
    }

    install(Auth) {
        bearer {
            loadTokens {
                val accessToken = authTokenManager.getAccessToken()
                val refreshToken = authTokenManager.getRefreshToken()
                PlatformLogger.d("Auth | accessToken: $accessToken")
                PlatformLogger.d("Auth | refreshToken: $refreshToken")
                if (accessToken != null && refreshToken != null)
                    BearerTokens(accessToken, refreshToken)
                else null
            }
            refreshTokens {
                val refreshToken = authTokenManager.getRefreshToken()
                val response = client.post("https://$URL/v1/auth/refresh") {
                    setBody(AuthRefreshTokenRequest(refreshToken!!))
                }.body<AuthTokenResponse>()
                authTokenManager.setToken(response)
                BearerTokens(
                    accessToken = response.accessToken,
                    refreshToken = response.refreshToken
                )
            }
        }
    }

    install(Logging) {
        logger = object : Logger {
            override fun log(message: String) {
                PlatformLogger.d(message)
            }
        }
        level = LogLevel.ALL
    }

    install(HttpCache)

    defaultRequest {

        headers {
            accept(ContentType.Application.Json)
            accept(ContentType.MultiPart.FormData)
        }

        contentType(ContentType.Application.Json)


        url {
            protocol = URLProtocol.HTTPS
            host = URL
        }
    }

    block()
}