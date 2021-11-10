package app.hdj.datepick.domain

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DomainModule {

    @get:[Binds]
    val FirebaseAuthenticator.authenticator: Authenticator

}