package app.hdj.datepick.data.utils

import com.russhwolf.settings.KeychainSettings
import com.russhwolf.settings.Settings

actual class EncryptedSettingsHolder {
    actual val settings: Settings get() = KeychainSettings("auth")
}