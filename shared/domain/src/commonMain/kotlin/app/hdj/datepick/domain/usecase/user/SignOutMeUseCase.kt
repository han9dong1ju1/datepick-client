package app.hdj.datepick.domain.usecase.user

import app.hdj.datepick.domain.repository.MeRepository
import app.hdj.datepick.domain.FirebaseAuthenticator
import app.hdj.datepick.utils.Inject
import app.hdj.datepick.utils.Singleton
import kotlinx.coroutines.flow.onCompletion

@Singleton
class SignOutMeUseCase @Inject constructor(
    private val authenticator: FirebaseAuthenticator,
    private val meRepository: MeRepository
) {

    fun execute() = meRepository.signOut()
        .onCompletion { if (it == null) authenticator.signOut() }

}