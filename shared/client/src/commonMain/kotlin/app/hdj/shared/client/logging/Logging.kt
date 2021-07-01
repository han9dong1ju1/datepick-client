package app.hdj.shared.client.logging

import app.hdj.shared.firebase.FirebaseAnalytics
import app.hdj.shared.firebase.analytics
import dev.gitlive.firebase.Firebase

interface Logging {

    val analytics: FirebaseAnalytics get() = Firebase.analytics

    fun log(key: String, vararg params: Pair<String, String>) {
        analytics.logEvent(key, *params)
    }

    fun setUserProperty(key: String, value: String) {
        analytics.setUserProperty(key, value)
    }

}