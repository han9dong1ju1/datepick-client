package app.hdj.datepick.data.remote.client

import app.hdj.datepick.android.BuildKonfig.kakaoApiKey
import app.hdj.datepick.utils.AppInfo
import app.hdj.datepick.utils.PlatformLogger
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.*
import kotlinx.serialization.json.Json

@Suppress("FunctionName")
fun <T : HttpClientEngineConfig> KakaoApiHttpClient(
    engineFactory: HttpClientEngineFactory<T>,
    block: HttpClientConfig<T>.() -> Unit = {}
) = HttpClient(engineFactory) {

    expectSuccess = false

    install(ContentNegotiation) {
        register(
            contentType = ContentType.Application.Json,
            converter = KotlinxSerializationConverter(
                Json {
                    ignoreUnknownKeys = true
                    allowSpecialFloatingPointValues = true
                    useArrayPolymorphism = true
                    prettyPrint = true
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
        header("Authorization", "KakaoAK $kakaoApiKey")

        headers {
            accept(ContentType.Application.Json)
        }

        contentType(ContentType.Application.Json)

        url {
            protocol = URLProtocol.HTTPS
            host = "dapi.kakao.com"
        }
    }

    block()
}