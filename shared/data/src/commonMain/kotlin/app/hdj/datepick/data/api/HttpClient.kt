package app.hdj.datepick.data.api

import app.hdj.datepick.utils.AppInfo
import app.hdj.datepick.domain.Authenticator
import app.hdj.datepick.utils.PlatformLogger
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
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
            ignoreUnknownKeys = true
            allowSpecialFloatingPointValues = true
            useArrayPolymorphism = true
            prettyPrint = true
            allowStructuredMapKeys = true
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

    defaultRequest {
        if (authenticator.idToken != null) {
            header("Authentication", "Bearer ${authenticator.idToken}")
        }

        contentType(ContentType.Application.Json)
        accept(ContentType.Application.Json)
        accept(ContentType.Text.Plain)

        url {
            protocol = URLProtocol.HTTPS
            host = "empty-parrot-42.loca.lt"
        }
    }

    block()
}