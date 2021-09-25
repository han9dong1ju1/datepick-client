@file:Suppress("TestFunctionName")

package app.hdj.datepick.data

import app.hdj.datepick.data.api.ApiResponse
import app.hdj.datepick.data.api.DatePickHttpClient
import app.hdj.datepick.utils.PlatformLogger
import app.hdj.datepick.utils.exception.ApiResponseException
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.mock.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.util.date.*
import io.ktor.utils.io.*
import kotlinx.serialization.*

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
                    content = kotlinx.serialization.json.Json.encodeToString(customApiResponse),
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

    install(JsonFeature) {
        serializer = KotlinxSerializer(
            kotlinx.serialization.json.Json {
                prettyPrint = true
                isLenient = true
            }
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

        validateResponse {
            val response = it.receive<ApiResponse<Unit?>>()
            val error = response.error
            if (error != null) throw ClientRequestException(it, error)
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