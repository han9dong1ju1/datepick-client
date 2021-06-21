package app.hdj.shared.client.utils

import com.russhwolf.settings.AppleSettings
import com.russhwolf.settings.Settings
import platform.Foundation.NSUserDefaults

actual val settings: Settings = AppleSettings(NSUserDefaults.new()!!)