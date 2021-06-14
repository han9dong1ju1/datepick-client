package app.hdj.firebase

import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase

actual object FirebaseAnalytics {


    actual fun setUserProperty(key: String, value: String) {
        Firebase.analytics.setUserProperty(key, value)
    }

    actual fun logEvent(name: String, vararg params: Pair<String, String>) {
        Firebase.analytics.logEvent(name) {
            params.forEach { (key, value) -> param(key, value) }
        }
    }

}