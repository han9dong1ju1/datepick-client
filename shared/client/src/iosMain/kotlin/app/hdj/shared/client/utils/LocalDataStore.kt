package app.hdj.shared.client.utils

import com.russhwolf.settings.AppleSettings
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.coroutines.FlowSettings
import com.russhwolf.settings.coroutines.toFlowSettings
import kotlinx.coroutines.ExperimentalCoroutinesApi
import platform.Foundation.NSUserDefaults

@OptIn(ExperimentalSettingsApi::class, ExperimentalCoroutinesApi::class)
class LocalDataStore(name: String? = null) : DataStoreDelegate(
    AppleSettings(
        if (name != null) {
            NSUserDefaults(name)
        } else {
            NSUserDefaults.standardUserDefaults
        }
    ).toFlowSettings()
)