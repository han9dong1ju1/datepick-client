package app.hdj.datepick.android.di

import android.content.Context
import app.hdj.datepick.android.BuildConfig
import app.hdj.shared.client.domain.entity.AppConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideAppConfig(
        @ApplicationContext context: Context
    ) = AppConfig(
        androidPackageName = context.packageName,
        version = BuildConfig.VERSION_NAME
    )

}