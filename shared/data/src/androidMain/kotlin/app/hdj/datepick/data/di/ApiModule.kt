package app.hdj.datepick.data.di

import app.hdj.datepick.data.remote.api.*
import app.hdj.datepick.data.remote.client.DatepickApiHttpClient
import app.hdj.datepick.data.remote.client.KakaoApiHttpClient
import app.hdj.datepick.data.utils.AuthTokenManager
import app.hdj.datepick.utils.AppInfo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    @Singleton
    fun provideAuthApi(@Named("datepick") client: HttpClient): AuthApi = AuthApiImp(client)

    @Provides
    @Singleton
    fun provideUserApi(
        @Named("datepick") client: HttpClient
    ): UserApi = UserApiImp(client)

    @Provides
    @Singleton
    fun provideFeaturedApi(
        @Named("datepick") client: HttpClient
    ): FeaturedApi = FeaturedApiImp(client)

    @Provides
    @Singleton
    fun providePlaceApi(
        @Named("datepick") client: HttpClient
    ): PlaceApi = PlaceApiImp(client)

    @Provides
    @Singleton
    fun provideCourseApi(
        @Named("datepick") client: HttpClient
    ): CourseApi = CourseApiImp(client)

    @Provides
    @Singleton
    fun provideKakaoPlaceSearchApi(
        @Named("kakao") client: HttpClient
    ): KakaoPlaceSearchApi = KakaoPlaceSearchApiImp(client)

    @Provides
    @Singleton
    @Named("datepick")
    fun provideDatepickApiHttpClient(
        authTokenManager: AuthTokenManager,
        appInfo: AppInfo
    ): HttpClient = DatepickApiHttpClient(OkHttp, authTokenManager, appInfo)

    @Provides
    @Singleton
    @Named("kakao")
    fun provideKakaoApiHttpClient(
        appInfo: AppInfo
    ): HttpClient = KakaoApiHttpClient(OkHttp, appInfo)

}
