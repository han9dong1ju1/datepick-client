package app.hdj.firebase

import dev.gitlive.firebase.Firebase

expect object FirebaseAnalytics {

    fun setUserProperty(key: String, value: String)

    fun logEvent(name: String, vararg params: Pair<String, String>)

}

val Firebase.analytics get() = FirebaseAnalytics