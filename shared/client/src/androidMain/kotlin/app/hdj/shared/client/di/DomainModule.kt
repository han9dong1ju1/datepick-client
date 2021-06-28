package app.hdj.shared.client.di

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import app.hdj.shared.client.data.repo.DaggerCourseRepositoryImp
import app.hdj.shared.client.data.repo.DaggerPlaceRepositoryImp
import app.hdj.shared.client.data.repo.DaggerSettingRepositoryImp
import app.hdj.shared.client.data.repo.DaggerUserRepositoryImp
import app.hdj.shared.client.domain.repo.CourseRepository
import app.hdj.shared.client.domain.repo.PlaceRepository
import app.hdj.shared.client.domain.repo.SettingRepository
import app.hdj.shared.client.domain.repo.UserRepository
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

@Module
@InstallIn(SingletonComponent::class)
interface DomainModule {

    @get:[Binds]
    val DaggerUserRepositoryImp.provideUserRepository: UserRepository

    @get:[Binds]
    val DaggerSettingRepositoryImp.provideSettingRepository: SettingRepository

    @get:[Binds]
    val DaggerPlaceRepositoryImp.providePlaceRepository: PlaceRepository

    @get:[Binds]
    val DaggerCourseRepositoryImp.provideCourseRepository: CourseRepository

}