package app.hdj.shared.client.data

import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*

private const val API_CLIENT_HOST_URL = "https://localhost:8080/"

fun createHttpClient(isDebugMode : Boolean) = HttpClient {
    install(JsonFeature) {
        serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
            isLenient = true
            ignoreUnknownKeys = true
        })
    }

    if (isDebugMode) {
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.INFO
        }
    }
}