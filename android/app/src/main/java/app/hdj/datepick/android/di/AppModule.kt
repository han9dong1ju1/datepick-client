package app.hdj.datepick.android.di

import app.hdj.datepick.android.BuildConfig
import app.hdj.shared.client.domain.entity.AppConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideAppConfig() = AppConfig(BuildConfig.VERSION_CODE)

}