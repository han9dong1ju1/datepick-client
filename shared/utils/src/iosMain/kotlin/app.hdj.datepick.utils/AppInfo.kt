package app.hdj.datepick.utils

import platform.Foundation.NSBundle

actual class AppInfo {
    actual val appId: String = requireNotNull(NSBundle.mainBundle().bundleIdentifier)
    actual val debug: Boolean = Platform.isDebugBinary
    actual val os: String = Platform.osFamily.name.lowercase()
}