package app.hdj.shared.client.di

import app.hdj.client.BuildConfig
import app.hdj.shared.client.data.api.UserApi
import app.hdj.shared.client.data.createHttpClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    @Singleton
    internal fun provideHttpClient(): HttpClient = createHttpClient(BuildConfig.DEBUG)

    @Provides
    @Singleton
    internal fun provideUserApi(client: HttpClient): UserApi = UserApi(client)

}