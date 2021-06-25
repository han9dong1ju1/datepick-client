package app.hdj.shared.client.di

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import app.hdj.client.BuildConfig
import app.hdj.shared.client.data.ApiClient
import app.hdj.shared.client.data.DaggerAuthenticatorImp
import app.hdj.shared.client.data.api.DaggerUserApi
import app.hdj.shared.client.data.api.UserApi
import app.hdj.shared.client.data.datastore.AppDataStore
import app.hdj.shared.client.data.datastore.AuthDataStore
import app.hdj.shared.client.utils.AndroidAppDataStore
import app.hdj.shared.client.utils.Authenticator
import app.hdj.shared.client.utils.AuthenticatorImp
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
import io.ktor.client.*
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

    @get:[Binds]
    val DaggerAuthenticatorImp.authenticator : Authenticator

}