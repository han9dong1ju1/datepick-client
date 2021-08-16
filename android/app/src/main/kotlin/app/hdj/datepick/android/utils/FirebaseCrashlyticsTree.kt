package app.hdj.datepick.android.utils

import android.util.Log
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import timber.log.Timber

class FirebaseCrashlyticsTree : Timber.Tree() {

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {

        if (priority == Log.VERBOSE || priority == Log.DEBUG) {
            return
        }

        Firebase.crashlytics.log("$tag | $message")
        t?.let { Firebase.crashlytics.recordException(it) }

    }

}