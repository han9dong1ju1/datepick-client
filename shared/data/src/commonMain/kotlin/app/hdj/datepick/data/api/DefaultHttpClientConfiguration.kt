package app.hdj.datepick.data.api

import app.hdj.datepick.utils.*
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*

@Singleton
class DefaultHttpClientConfiguration @Inject constructor(
    private val authenticator: FirebaseAuthenticator,
    private val appInfo: AppInfo
) {

    fun <T : HttpClientEngineConfig> defaultHttpClientConfiguration(
        additionalConfig: HttpClientConfig<T>.() -> Unit = {}
    ): HttpClientConfig<T>.() -> Unit = {

        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }

        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    PlatformLogger.d(message)
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