package app.hdj.datepick.data.api

import app.hdj.datepick.utils.*
import app.hdj.datepick.utils.exception.ApiResponseException
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

@Singleton
class DefaultHttpClientConfiguration @Inject constructor(
    private val authenticator: FirebaseAuthenticator,
    private val appInfo: AppInfo
) {

    fun <T : HttpClientEngineConfig> defaultHttpClientConfiguration(
        additionalConfig: HttpClientConfig<T>.() -> Unit = {}
    ): HttpClientConfig<T>.() -> Unit = {

        developmentMode = appInfo.debug
        expectSuccess = false

        install(HttpTimeout) {
            requestTimeoutMillis = 10_000L
        }

        install(JsonFeature) {
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                prettyPrint = true
                isLenient = true
            })
        }

        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    PlatformLogger.d(message)
                }
            }
            level = LogLevel.ALL
        }

        HttpResponseValidator {
            handleResponseException { exception ->
                val clientException =
                    exception as? ClientRequestException ?: return@handleResponseException

                val response = clientException.response

                val apiResponse: ApiResponse<*> = response.receive()

                if (!response.status.isSuccess()) {
                    val exceptionResponseText = response.readText()
                    throw ApiResponseException(
                        apiResponse.code,
                        """$exceptionResponseText | ${apiResponse.message}"""
                    )
                }
            }
        }

        defaultRequest {
            if (authenticator.idToken != null) {
                header("Authentication", "Token ${authenticator.idToken}")
            }

            host = "localhost"
            port = 8080
        }

        additionalConfig()
    }

}