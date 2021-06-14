package app.hdj.firebase

import com.google.firebase.crashlytics.ktx.setCustomKeys
import dev.gitlive.firebase.Firebase

actual class FirebaseCrashlytics constructor(val android: com.google.firebase.crashlytics.FirebaseCrashlytics) {

    actual fun setCustomKeys(vararg keyValues: Pair<String, Any>) {
        android.setCustomKeys {
            keyValues.forEach { (key, value) ->
                when (value) {
                    is String -> key(key, value)
                    is Int -> key(key, value)
                    is Float -> key(key, value)
                    is Boolean -> key(key, value)
                    is Long -> key(key, value)
                    is Double -> key(key, value)
                }
            }
        }
    }

    actual fun log(message: String) {
        android.log(message)
    }

    actual fun nonFatalException(throwable: Throwable) {
        android.recordException(throwable)
    }

}

actual val Firebase.crashlytics: FirebaseCrashlytics
    get() = FirebaseCrashlytics(com.google.firebase.crashlytics.FirebaseCrashlytics.getInstance())