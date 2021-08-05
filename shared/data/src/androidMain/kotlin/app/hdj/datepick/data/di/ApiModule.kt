package app.hdj.datepick.data.di

import app.hdj.datepick.data.api.DefaultHttpClientConfiguration
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    @Singleton
    fun provideHttpClient(
        configuration: DefaultHttpClientConfiguration
    ) = HttpClient(
        OkHttp,
        configuration.defaultHttpClientConfiguration {

        }
    )

}