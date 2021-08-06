@file:Suppress("EnumEntryName")

package app.hdj.datepick.utils

data class AppInfo(
    val appId: String,
    val debug: Boolean,
    val os: Os
) {

    enum class Os {
        Android, iOS
    }

}