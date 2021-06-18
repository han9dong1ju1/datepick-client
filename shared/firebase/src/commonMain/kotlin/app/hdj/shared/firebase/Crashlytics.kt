package app.hdj.shared.firebase

import dev.gitlive.firebase.Firebase

expect class FirebaseCrashlytics {

    fun setCustomKeys(vararg keyValues : Pair<String, Any>)

    fun log(message : String)

    fun nonFatalException(throwable: Throwable)

}


expect val Firebase.crashlytics: FirebaseCrashlytics