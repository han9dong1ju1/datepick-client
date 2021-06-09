package app.hdj.firebase

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.google.firebase.remoteconfig.ktx.get
import com.google.firebase.remoteconfig.ktx.remoteConfig
import dev.gitlive.firebase.Firebase

actual class RemoteConfig(val android: FirebaseRemoteConfig) {

    actual var configSettings: RemoteConfigSettings
        get() = RemoteConfigSettings(android.info.configSettings.toBuilder())
        set(value) {
            android.setConfigSettingsAsync(value.android.build())
        }

    actual fun fetch(cacheExpiration: Long) {
        android.fetch(cacheExpiration).addOnSuccessListener {
            android.activate()
        }
    }

}

actual val Firebase.remoteConfig: RemoteConfig
    get() = RemoteConfig(com.google.firebase.ktx.Firebase.remoteConfig)

actual class RemoteConfigSettings(val android: FirebaseRemoteConfigSettings.Builder) {

    actual var minimumFetchInterval: Long
        get() = android.minimumFetchIntervalInSeconds
        set(value) {
            android.minimumFetchIntervalInSeconds = value
        }

}

actual fun remoteConfigSettings(block: RemoteConfigSettings.() -> Unit): RemoteConfigSettings {
    return RemoteConfigSettings(FirebaseRemoteConfigSettings.Builder()).apply(block)
}

actual inline fun <reified T> RemoteConfig.get(key: String): T? {
    return kotlin.runCatching {
        val value = android[key]
        when (T::class) {
            String::class -> value.asString()
            Double::class -> value.asDouble()
            Long::class -> value.asLong()
            Boolean::class -> value.asBoolean()
            else -> throw IllegalAccessError()
        }
    }.getOrNull() as? T?
}