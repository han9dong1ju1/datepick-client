package app.hdj.datepick.domain.usecase.user

import app.hdj.datepick.domain.repository.MeRepository
import app.hdj.datepick.domain.Authenticator
import app.hdj.datepick.domain.isStateSucceed
import app.hdj.datepick.utils.Inject
import app.hdj.datepick.utils.Singleton
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach

@Singleton
class UnregisterMeUseCase @Inject constructor(
    private val authenticator: Authenticator,
    private val meRepository: MeRepository,
) {

    operator fun invoke(param: Param) =
        meRepository.unregister(param.reason)
            .onEach {
                if (it.isStateSucceed()) authenticator.signOut()
            }

    data class Param(
        val reason: String
    )

}