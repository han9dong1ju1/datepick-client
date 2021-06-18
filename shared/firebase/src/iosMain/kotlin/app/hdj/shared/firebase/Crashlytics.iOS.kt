package app.hdj.shared.firebase

import cocoapods.FirebaseCrashlytics.FIRCrashlytics
import dev.gitlive.firebase.Firebase
import platform.Foundation.NSError
import app.hdj.shared.firebase.FirebaseCrashlytics

actual class FirebaseCrashlytics constructor(private val ios: FIRCrashlytics) {

    actual fun setCustomKeys(vararg keyValues: Pair<String, Any>) {
        ios.setCustomKeysAndValues(keysAndValues = mapOf(*keyValues))
    }

    actual fun log(message: String) {
        ios.log(message)
    }

    actual fun nonFatalException(throwable: Throwable) {
        ios.recordError(NSError.errorWithDomain(throwable.message, 0, null))
    }

}

actual val Firebase.crashlytics: FirebaseCrashlytics
    get() = app.hdj.shared.firebase.FirebaseCrashlytics(FIRCrashlytics.crashlytics())