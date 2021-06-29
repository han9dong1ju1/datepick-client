package app.hdj.shared.client.utils

import app.hdj.shared.client.domain.AppStoreInfo
import io.ktor.client.*
import io.ktor.client.request.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import platform.Foundation.NSBundle

class VersionChecker : KoinComponent {

    private val client by inject<HttpClient>()

    suspend fun getAppUpdateInfo(): ApplicationUpdateInfo {
        val mainBundle = NSBundle.mainBundle().infoDictionary ?: return false

        val identifier = mainBundle["CFBundleIdentifier"].toString()

        val info = client.get<AppStoreInfo>(URL) {
            parameter("bundleId", identifier)
        }

        val currentVersion = mainBundle["CFBundleShortVersionString"].toString()

        return ApplicationUpdateInfo(
            currentVersion,
            currentVersion < info.results.first().version
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