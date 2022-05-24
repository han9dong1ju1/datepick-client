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
    @Named("real")
    fun provideCategoryApi(): CategoryApi = CategoryApiImp()

    @Provides
    @Singleton
    @Named("real")
    fun provideAuthApi(): AuthApi = AuthApiImp()

    @Provides
    @Singleton
    @Named("real")
    fun provideUserApi(): UserApi = UserApiImp()

    @Provides
    @Singleton
    @Named("real")
    fun provideFeaturedApi(): FeaturedApi = FeaturedApiImp()

    @Provides
    @Singleton
    @Named("real")
    fun providePlaceApi(): PlaceApi = PlaceApiImp()

    @Provides
    @Singleton
    @Named("real")
    fun provideCourseApi(): CourseApi = CourseApiImp()

    @Provides
    @Singleton
    @Named("real")
    fun provideDistrictApi(): DistrictApi = DistrictApiImp()

    @Provides
    @Singleton
    fun provideKakaoPlaceSearchApi(
        @Named("kakao") client: HttpClient
    ): KakaoPlaceSearchApi = KakaoPlaceSearchApiImp(client)


    @Provides
    @Singleton
    @Named("kakao")
    fun provideKakaoApiHttpClient(): HttpClient = KakaoApiHttpClient(OkHttp)

}
