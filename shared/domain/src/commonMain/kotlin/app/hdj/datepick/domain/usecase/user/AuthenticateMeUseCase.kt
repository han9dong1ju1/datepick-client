package app.hdj.datepick.domain.usecase.user

import app.hdj.datepick.domain.repository.MeRepository
import app.hdj.datepick.domain.FirebaseAuthenticator
import app.hdj.datepick.utils.Inject
import app.hdj.datepick.utils.Singleton
import dev.gitlive.firebase.auth.AuthCredential
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow

@Singleton
class AuthenticateMeUseCase @Inject constructor(
    private val authenticator: FirebaseAuthenticator,
    private val meRepository: MeRepository
) {

    fun execute(credential: AuthCredential) = flow {
        authenticator.signInWithCredential(credential)
        emitAll(meRepository.signIn())
    }

}