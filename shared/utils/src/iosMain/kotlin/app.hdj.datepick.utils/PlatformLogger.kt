package app.hdj.datepick.utils

actual object PlatformLogger {

    actual fun d(message: String) {
        println("Logger : $message")
    }

    actual fun e(throwable: Throwable) {
        println(throwable.message)
    }

}