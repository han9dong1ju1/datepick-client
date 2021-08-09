package app.hdj.datepick.data.di

import app.hdj.datepick.data.api.DatePickHttpClient
import app.hdj.datepick.utils.AppInfo
import app.hdj.datepick.utils.Authenticator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.engine.okhttp.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    @Singleton
    fun provideHttpClient(
        authenticator: Authenticator,
        appInfo: AppInfo
    ) = DatePickHttpClient(
        OkHttp,
        authenticator,
        appInfo
    )

}