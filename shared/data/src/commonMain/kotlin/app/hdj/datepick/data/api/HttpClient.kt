package app.hdj.datepick.data.api

import app.hdj.datepick.utils.AppInfo
import app.hdj.datepick.utils.Authenticator
import app.hdj.datepick.utils.PlatformLogger
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


@Suppress("FunctionName")
fun <T : HttpClientEngineConfig> DatePickHttpClient(
    engineFactory: HttpClientEngineFactory<T>,
    authenticator: Authenticator,
    appInfo: AppInfo,
    block: HttpClientConfig<T>.() -> Unit = {}
) = HttpClient(engineFactory) {

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

        validateResponse {
            val response = it.receive<ApiResponse<Unit?>>()
            if (response.code > 300) throw ClientRequestException(it, response.message)
        }

        handleResponseException { exception ->
            val clientException =
                exception as? ClientRequestException ?: return@handleResponseException

            val response = clientException.response

            if (!response.status.isSuccess()) {
                throw ApiResponseException(
                    response.status.value,
                    clientException.message
                )
            }
        }
    }

    defaultRequest {
        if (authenticator.idToken != null) {
            header("Authentication", "Token ${authenticator.idToken}")
        }

        contentType(ContentType.Application.Json)
        accept(ContentType.Application.Json)
        accept(ContentType.Text.Plain)

        host = "localhost"
        port = 8080
    }

    block()
}