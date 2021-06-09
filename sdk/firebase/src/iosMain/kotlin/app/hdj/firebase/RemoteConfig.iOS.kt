package app.hdj.firebase

import cocoapods.FirebaseRemoteConfig.FIRRemoteConfig
import cocoapods.FirebaseRemoteConfig.FIRRemoteConfigSettings
import dev.gitlive.firebase.Firebase

actual class RemoteConfig(val ios: FIRRemoteConfig) {

    actual var configSettings: RemoteConfigSettings
        get() = RemoteConfigSettings(ios.configSettings)
        set(value) {
            ios.configSettings = value.ios
        }

    actual fun fetch(cacheExpiration: Long) {
        ios.fetchWithExpirationDuration(cacheExpiration.toDouble()) { _, _ ->

        }
    }
}

actual val Firebase.remoteConfig: RemoteConfig
    get() = RemoteConfig(FIRRemoteConfig())

actual class RemoteConfigSettings(val ios: FIRRemoteConfigSettings) {
    actual var minimumFetchInterval: Long
        get() = ios.minimumFetchInterval.toLong()
        set(value) {
            ios.minimumFetchInterval = value.toDouble()
        }

}

actual fun remoteConfigSettings(block: RemoteConfigSettings.() -> Unit): RemoteConfigSettings {
    return RemoteConfigSettings(FIRRemoteConfigSettings()).apply(block)
}

actual inline fun <reified T> RemoteConfig.get(key: String): T? {
    return kotlin.runCatching {
        val value = ios.configValueForKey(key)
        when (T::class) {
            String::class -> value.stringValue()
            Double::class -> value.numberValue()
            Long::class -> value.numberValue()
            Boolean::class -> value.boolValue()
            else -> throw IllegalStateException()
        }
    }.getOrNull() as? T?
}