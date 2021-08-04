package app.hdj.datepick.data.di

import app.hdj.datepick.data.api.DefaultHttpClientConfiguration
import app.hdj.datepick.utils.FirebaseAuthenticator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import timber.log.Timber
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    @Singleton
    fun provideHttpClient(
        configuration: DefaultHttpClientConfiguration
    ) = HttpClient(
        CIO,
        configuration.defaultHttpClientConfiguration {

        }
    )

}