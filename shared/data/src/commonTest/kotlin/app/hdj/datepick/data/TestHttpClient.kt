@file:Suppress("TestFunctionName")

package app.hdj.datepick.data

import app.hdj.datepick.data.api.ApiResponse
import app.hdj.datepick.data.api.DatePickHttpClient
import app.hdj.datepick.utils.PlatformLogger
import app.hdj.datepick.utils.exception.ApiResponseException
import io.ktor.client.engine.mock.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

val DefaultTestHttpClient = TestDatePickHttpClient()

fun TestDatePickHttpClient(
    customStatusCode: HttpStatusCode? = null,
    customApiResponse: ApiResponse<*>? = null
) = DatePickHttpClient(
    MockEngine,
    MockFirebaseAuthenticator(),
    TestAndroidAppInfo
) {

    if (customApiResponse != null && customStatusCode != null) {
        engine {
            addHandler {
                respond(
                    content = Json.encodeToString(customApiResponse),
                    status = customStatusCode,
                    headers = headersOf(
                        HttpHeaders.ContentType,
                        ContentType.Application.Json.toString()
                    )
                )
            }
        }
    }

    developmentMode = true
    expectSuccess = false

    install(HttpTimeout) {
        requestTimeoutMillis = 10_000L
    }

    install(ContentNegotiation) {
        register(
            contentType = ContentType.Application.Json,
            converter = KotlinxSerializationConverter(
                Json {
                    prettyPrint = true
                    isLenient = true
                }
            )
        )
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

        validateResponse { response ->
            if (!response.status.isSuccess())
                throw ClientRequestException(response, response.bodyAsText())
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
//        header("Authentication", "Token ${authenticator.idToken}")
        contentType(ContentType.Application.Json)
        accept(ContentType.Application.Json)
        host = "localhost"
        port = 8080
    }

}