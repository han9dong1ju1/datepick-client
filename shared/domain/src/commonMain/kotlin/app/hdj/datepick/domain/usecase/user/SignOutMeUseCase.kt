package app.hdj.datepick.domain.usecase.user

import app.hdj.datepick.domain.repository.MeRepository
import app.hdj.datepick.utils.FirebaseAuthenticator
import app.hdj.datepick.utils.Inject
import app.hdj.datepick.utils.Singleton
import dev.gitlive.firebase.auth.AuthCredential
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion

@Singleton
class SignOutMeUseCase @Inject constructor(
    private val authenticator: FirebaseAuthenticator,
    private val meRepository: MeRepository
) {

    fun execute() = meRepository.signOut()
        .onCompletion { if (it == null) authenticator.signOut() }

}