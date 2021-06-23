package app.hdj.shared.client.di

import app.hdj.shared.client.data.DaggerPlaceRepositoryImp
import app.hdj.shared.client.data.DaggerSettingRepositoryImp
import app.hdj.shared.client.data.DaggerUserRepositoryImp
import app.hdj.shared.client.data.api.PlaceApi
import app.hdj.shared.client.data.api.UserApi
import app.hdj.shared.client.data.cache.PlaceCache
import app.hdj.shared.client.data.cache.UserCache
import app.hdj.shared.client.domain.repo.PlaceRepository
import app.hdj.shared.client.domain.repo.SettingRepository
import app.hdj.shared.client.domain.repo.UserRepository
import app.hdj.shared.client.utils.LocalDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    @Singleton
    internal fun provideUserRepository(api: UserApi, cache: UserCache): UserRepository =
        DaggerUserRepositoryImp(api, cache)

    @Provides
    @Singleton
    internal fun providePlaceRepository(api: PlaceApi, cache: PlaceCache): PlaceRepository =
        DaggerPlaceRepositoryImp(api, cache)

    @Provides
    @Singleton
    internal fun provideSettingRepository(localDataStore: LocalDataStore): SettingRepository =
        DaggerSettingRepositoryImp(localDataStore)

}