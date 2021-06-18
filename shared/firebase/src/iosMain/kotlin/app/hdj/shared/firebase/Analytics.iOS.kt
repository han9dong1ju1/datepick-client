package app.hdj.shared.firebase

import cocoapods.FirebaseAnalytics.FIRAnalytics

actual object FirebaseAnalytics {

    actual fun setUserProperty(key: String, value: String) {
        FIRAnalytics.setUserPropertyString(key, value)
    }

    actual fun logEvent(name: String, vararg params: Pair<String, String>) {
        FIRAnalytics.logEventWithName(name, *params.toMap())
    }

}