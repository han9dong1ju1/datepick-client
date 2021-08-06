package app.hdj.datepick.utils.di

import android.content.Context
import app.hdj.datepick.utils.AppInfo
import app.hdj.datepick.utils.Authenticator
import app.hdj.datepick.utils.BuildConfig
import app.hdj.datepick.utils.FirebaseAuthenticator
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface UtilModule {

    @get:[Binds]
    val FirebaseAuthenticator.authenticator: Authenticator

    companion object {
        @Provides
        @Singleton
        fun provideAppInfo(@ApplicationContext context: Context) = AppInfo(
            context.packageName,
            BuildConfig.DEBUG,
            AppInfo.Os.Android
        )
    }


}