package app.hdj.firebase

import dev.gitlive.firebase.Firebase

expect val Firebase.remoteConfig: RemoteConfig

expect class RemoteConfig {

    var configSettings : RemoteConfigSettings

    fun fetch(cacheExpiration : Long)

}

expect inline fun <reified T> RemoteConfig.get(key: String): T?

expect fun remoteConfigSettings(block: RemoteConfigSettings.() -> Unit): RemoteConfigSettings

expect class RemoteConfigSettings {

    var minimumFetchInterval: Long

}


