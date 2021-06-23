package app.hdj.shared.client.utils

import com.russhwolf.settings.AppleSettings
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.coroutines.FlowSettings
import com.russhwolf.settings.coroutines.toFlowSettings
import kotlinx.coroutines.ExperimentalCoroutinesApi
import platform.Foundation.NSUserDefaults

@OptIn(ExperimentalSettingsApi::class, ExperimentalCoroutinesApi::class)
 class LocalDataStore(name: String? = null) : DataStoreDelegate() {

    private val appleSetting: AppleSettings

    init {
        val userDefaults = if (name != null) {
            NSUserDefaults(name)
        } else {
            NSUserDefaults.standardUserDefaults
        }

        appleSetting = AppleSettings(userDefaults)
    }

    override val settings: FlowSettings = appleSetting.toFlowSettings()


}