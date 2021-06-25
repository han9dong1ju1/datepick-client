package app.hdj.shared.client.di

import app.hdj.shared.client.data.cache.DaggerUserCache
import app.hdj.shared.client.data.cache.UserCache
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface CacheModule {

    @get:[Binds]
    val DaggerUserCache.userCache: UserCache

}