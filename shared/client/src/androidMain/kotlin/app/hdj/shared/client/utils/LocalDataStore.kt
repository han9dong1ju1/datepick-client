package app.hdj.shared.client.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ExperimentalSettingsImplementation
import com.russhwolf.settings.coroutines.FlowSettings
import com.russhwolf.settings.datastore.DataStoreSettings
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

internal val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

@Singleton
@OptIn(ExperimentalSettingsImplementation::class, ExperimentalSettingsApi::class)
class LocalDataStore @Inject constructor(
    @ApplicationContext val context: Context
) : DataStoreDelegate() {

    override val settings: FlowSettings = DataStoreSettings(context.dataStore)

}