package app.hdj.shared.client.data

import app.hdj.shared.client.data.datastore.AppDataStore
import app.hdj.shared.client.data.datastore.AuthDataStore
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*

private const val API_CLIENT_HOST_URL = "https://localhost:8080/"

object ApiClient {

    fun createHttpClient(
        isDebugMode: Boolean,
        authDataStore: AuthDataStore
    ) = HttpClient(CIO) {
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

        defaultRequest {
            headers {
                authDataStore.idToken.value?.let {
                    set("Authorization", "Bearer $it")
                }
            }
        }
    }
}