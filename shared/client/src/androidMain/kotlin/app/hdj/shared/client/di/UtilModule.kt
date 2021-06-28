package app.hdj.shared.client.di

import app.hdj.shared.client.data.api.DaggerAuthenticatorImp
import app.hdj.shared.client.data.api.Authenticator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface UtilModule {

    @get:[Binds]
    val DaggerAuthenticatorImp.provideAuthenticator: Authenticator

}