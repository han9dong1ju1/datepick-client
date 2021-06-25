package app.hdj.shared.client.utils

import app.hdj.shared.client.data.datastore.AppDataStore
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.coroutines.FlowSettings
import kotlinx.coroutines.ExperimentalCoroutinesApi

/*
AppleSettings(
if (name != null) {
    NSUserDefaults(name)
} else {
    NSUserDefaults.standardUserDefaults
}
).toFlowSettings()
*/

@OptIn(ExperimentalSettingsApi::class, ExperimentalCoroutinesApi::class)
class AppleAppDataStore(settings: FlowSettings) : AppDataStore(settings)