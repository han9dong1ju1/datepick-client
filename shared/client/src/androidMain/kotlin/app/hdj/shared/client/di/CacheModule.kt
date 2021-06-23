package app.hdj.shared.client.di

import app.hdj.client.BuildConfig
import app.hdj.shared.client.data.api.UserApi
import app.hdj.shared.client.data.cache.UserCache
import app.hdj.shared.client.data.createHttpClient
import app.hdj.shared.client.utils.DataStoreDelegate
import app.hdj.shared.client.utils.LocalDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CacheModule {

    @Provides
    @Singleton
    internal fun provideUserCache(): UserCache = UserCache()

}