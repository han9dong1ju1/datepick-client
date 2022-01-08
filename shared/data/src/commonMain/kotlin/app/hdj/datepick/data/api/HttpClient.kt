package app.hdj.datepick.data.api

import app.hdj.datepick.domain.Authenticator
import app.hdj.datepick.utils.AppInfo
import app.hdj.datepick.utils.PlatformLogger
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json


@OptIn(ExperimentalSerializationApi::class)
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
        requestTimeoutMillis = 5000L
        connectTimeoutMillis = 5000L
    }

    install(ContentNegotiation) {
        register(
            contentType = ContentType.Application.Json,
            converter = KotlinxSerializationConverter(
                Json {
                    ignoreUnknownKeys = true
                    allowSpecialFloatingPointValues = true
                    useArrayPolymorphism = true
                    prettyPrint = true
                    explicitNulls = false
                    allowStructuredMapKeys = true
                    coerceInputValues = true
                    useAlternativeNames = false
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

    defaultRequest {
        if (authenticator.idToken != null) {
            header("Authorization", "Bearer ${authenticator.idToken}")
        }

        headers {
            accept(ContentType.Application.Json)
            accept(ContentType.MultiPart.FormData)
        }

        contentType(ContentType.Application.Json)

        url {
            protocol = URLProtocol.HTTPS
            host = "datepick.app"
        }
    }

    block()
}