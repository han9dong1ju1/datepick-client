package app.hdj.datepick.android.di

import android.content.Context
import app.hdj.datepick.utils.AppInfo
import app.hdj.datepick.android.BuildConfig
import coil.ImageLoader
import coil.disk.DiskCache
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
    fun provideAppInfo(@ApplicationContext context: Context) = AppInfo(
        context.packageName,
        BuildConfig.DEBUG,
        AppInfo.Os.Android,
        BuildConfig.VERSION_NAME
    )

    @Provides
    @Singleton
    fun provideImageLoader(@ApplicationContext context: Context): ImageLoader =
        ImageLoader.Builder(context)
            .crossfade(true)
            .diskCache(
                DiskCache.Builder()
                    .directory(context.cacheDir)
                    .build()
            )
            .build()


}