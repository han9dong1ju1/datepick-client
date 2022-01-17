package app.hdj.datepick.data.di

import app.hdj.datepick.data.api.*
import app.hdj.datepick.data.utils.FirebaseAuthenticator
import app.hdj.datepick.domain.Authenticator
import app.hdj.datepick.utils.AppInfo
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.engine.okhttp.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface ApiModule {

    @get:[Binds]
    val UserApiImp.userApi : UserApi

    @get:[Binds]
    val FeaturedApiImp.featuredApi : FeaturedApi

    @get:[Binds]
    val PlaceApiImp.placeApi : PlaceApi

    @get:[Binds]
    val FirebaseAuthenticator.authenticator: Authenticator

    companion object {

        @Provides
        @Singleton
        fun provideHttpClient(
            authenticator: Authenticator,
            appInfo: AppInfo
        ) = DatePickHttpClient(
            OkHttp,
            authenticator,
            appInfo
        )

    }

}