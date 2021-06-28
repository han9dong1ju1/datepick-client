package app.hdj.shared.client.di

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import app.hdj.shared.client.data.api.DaggerAuthenticatorImp
import app.hdj.shared.client.data.datastore.AppDataStore
import app.hdj.shared.client.data.store.AndroidAppDataStore
import app.hdj.shared.client.data.api.Authenticator
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ExperimentalSettingsImplementation
import com.russhwolf.settings.coroutines.FlowSettings
import com.russhwolf.settings.datastore.DataStoreSettings
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore(name = "settings")

@Module
@InstallIn(SingletonComponent::class)
@OptIn(ExperimentalSettingsApi::class, ExperimentalSettingsImplementation::class)
interface DataModule {

    companion object {

        @Provides
        @Singleton
        internal fun provideDataStoreSettings(@ApplicationContext context: Context): FlowSettings =
            DataStoreSettings(context.dataStore)

    }

    @get:[Binds]
    val AndroidAppDataStore.dataStore : AppDataStore

}