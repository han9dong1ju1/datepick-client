package app.hdj.datepick.domain.usecase.user

import app.hdj.datepick.domain.repository.MeRepository
import app.hdj.datepick.domain.FirebaseAuthenticator
import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.isStateSucceed
import app.hdj.datepick.domain.map
import app.hdj.datepick.utils.Inject
import app.hdj.datepick.utils.Singleton
import dev.gitlive.firebase.auth.AuthCredential
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

@OptIn(FlowPreview::class)
@Singleton
class AuthenticateMeUseCase @Inject constructor(
    private val authenticator: FirebaseAuthenticator,
    private val meRepository: MeRepository
) {

    fun execute(credential: AuthCredential) = flow {
        authenticator.signInWithCredential(credential)
        val flows : Flow<LoadState<Unit>> = meRepository
            .fetch()
            .flatMapConcat {
                when (it) {
                    is LoadState.Success, is LoadState.Loading -> flowOf(it.map { })
                    is LoadState.Failed ->
                        meRepository
                            .register("firebase", authenticator.idToken!!)
                            .map { registerState ->
                                if (registerState.isStateSucceed()) authenticator.refreshIdToken()
                                registerState
                            }
                }
            }
        emitAll(flows)
    }

}