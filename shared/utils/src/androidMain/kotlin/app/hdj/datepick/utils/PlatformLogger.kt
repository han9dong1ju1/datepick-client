package app.hdj.datepick.utils

import timber.log.Timber

actual object PlatformLogger {

    actual fun d(message: String) {
        Timber.d(message)
    }

    actual fun e(throwable: Throwable) {
        Timber.e(throwable)
    }

}