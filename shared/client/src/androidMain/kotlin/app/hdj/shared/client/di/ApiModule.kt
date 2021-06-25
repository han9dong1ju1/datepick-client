package app.hdj.shared.client.di

import app.hdj.client.BuildConfig
import app.hdj.shared.client.data.ApiClient
import app.hdj.shared.client.data.api.*
import app.hdj.shared.client.data.datastore.AuthDataStore
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface ApiModule {

    companion object {

        @Provides
        @Singleton
        internal fun provideHttpClient(authDataStore: AuthDataStore): HttpClient =
            ApiClient.createHttpClient(BuildConfig.DEBUG, authDataStore)

    }

    @get:[Binds]
    val DaggerUserApi.userApi: UserApi

    @get:[Binds]
    val DaggerPlaceApi.placeApi: PlaceApi

    @get:[Binds]
    val DaggerCourseApi.courseApi: CourseApi

}