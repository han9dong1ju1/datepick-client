package app.hdj.shared.client.utils

import app.hdj.shared.client.data.datastore.AppDataStore
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ExperimentalSettingsImplementation
import com.russhwolf.settings.coroutines.FlowSettings
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@OptIn(ExperimentalSettingsImplementation::class, ExperimentalSettingsApi::class)
class AndroidAppDataStore @Inject constructor(dataStoreSettings: FlowSettings) : AppDataStore(dataStoreSettings)