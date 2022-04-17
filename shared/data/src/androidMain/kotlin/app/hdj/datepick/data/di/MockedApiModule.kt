package app.hdj.datepick.data.di

import app.hdj.datepick.data.remote.api.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class MockedApiModule {

    @Provides
    @Singleton
    @Named("mocked")
    fun provideAuthApi(): AuthApi = fakeAuthApi()

    @Provides
    @Singleton
    @Named("mocked")
    fun provideUserApi(): UserApi = fakeUserApi()

    @Provides
    @Singleton
    @Named("mocked")
    fun provideFeaturedApi(): FeaturedApi = fakeFeaturedApi()

    @Provides
    @Singleton
    @Named("mocked")
    fun providePlaceApi(): PlaceApi = fakePlaceApi()

    @Provides
    @Singleton
    @Named("mocked")
    fun provideCourseApi(): CourseApi = fakeCourseApi()

    @Provides
    @Singleton
    @Named("mocked")
    fun provideDistrictApi(): DistrictApi = fakeDistrictApi()

}