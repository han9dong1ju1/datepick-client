package app.hdj.shared.client.utils

import app.hdj.shared.client.domain.AppStoreInfo
import app.hdj.shared.client.domain.entity.AppConfig
import io.ktor.client.*
import io.ktor.client.request.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import platform.Foundation.NSBundle

class VersionChecker : KoinComponent {

    private val appConfig by inject<AppConfig>()

    private val client by inject<HttpClient>()

    suspend fun getAppUpdateInfo(): ApplicationUpdateInfo {

        val info = client.get<AppStoreInfo>(URL) {
            parameter("bundleId", appConfig.iosBundleId)
        }

        val newVersionName = info.results.first().version

        return ApplicationUpdateInfo(
            newVersionName,
            appConfig.version < newVersionName
        )
    }

    companion object {
        const val URL = "https://itunes.apple.com/lookup"
    }

}

data class ApplicationUpdateInfo(
    val newVersionName: String,
    val isAppUpdateAvailable: Boolean
)