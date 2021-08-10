package app.hdj.datepick.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import app.hdj.datepick.data.datastore.*
import com.russhwolf.settings.AndroidSettings
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
import javax.inject.Singleton

internal val Context.dataStore by preferencesDataStore("")

@Module
@InstallIn(SingletonComponent::class)
interface DataStoreModule {

    @get:[Binds]
    val MeDataStoreImp.meDataStore: MeDataStore

    @get:[Binds]
    val PlaceDataStoreImp.placeDataStore: PlaceDataStore

    @get:[Binds]
    val CourseDataStoreImp.courseDataStore: CourseDataStore

    @get:[Binds]
    val FeaturedDataStoreImp.featuredDataStore: FeaturedDataStore

    companion object {

        @OptIn(ExperimentalSettingsApi::class, ExperimentalSettingsImplementation::class)
        @Provides
        @Singleton
        fun provideSettings(@ApplicationContext context: Context): FlowSettings =
            DataStoreSettings(context.dataStore)
    }

}