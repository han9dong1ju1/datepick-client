package app.hdj.datepick.domain.usecase.user

import app.hdj.datepick.domain.emitState
import app.hdj.datepick.utils.FirebaseAuthenticator
import app.hdj.datepick.utils.Inject
import app.hdj.datepick.utils.Singleton
import dev.gitlive.firebase.auth.AuthCredential
import kotlinx.coroutines.flow.flow

@Singleton
class AuthenticateMeUseCase @Inject constructor(private val authenticator: FirebaseAuthenticator) {

    fun execute(credential: AuthCredential) = flow {
        emitState {
            authenticator.signInGoogle(credential)
        }
    }

}