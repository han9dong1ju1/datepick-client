package app.hdj.datepick.data.di

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import app.hdj.datepick.data.storage.datastore.*
import app.hdj.datepick.data.storage.settings.AppSettingsImp
import app.hdj.datepick.data.utils.EncryptedSettingsHolder
import app.hdj.datepick.domain.settings.AppSettings
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ExperimentalSettingsImplementation
import com.russhwolf.settings.Settings
import com.russhwolf.settings.coroutines.FlowSettings
import com.russhwolf.settings.datastore.DataStoreSettings
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

const val DATA_STORE_NAME = "datepick-store"

internal val Context.dataStore by preferencesDataStore(DATA_STORE_NAME)

@Module
@InstallIn(SingletonComponent::class)
interface DataStoreModule {

    @get:[Binds]
    val MeDataStoreImp.meDataStore: MeDataStore

    @get:[Binds]
    val AppSettingsImp.appSettings: AppSettings

    companion object {

        @OptIn(ExperimentalSettingsApi::class, ExperimentalSettingsImplementation::class)
        @Provides
        @Singleton
        fun provideSettings(@ApplicationContext context: Context): FlowSettings =
            DataStoreSettings(context.dataStore)

        @Provides
        @Singleton
        @Named("encrypted")
        fun provideEncryptSettings(@ApplicationContext context: Context): Settings =
            EncryptedSettingsHolder(context).settings
    }

}