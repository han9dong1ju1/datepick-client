package app.hdj.datepick.domain.usecase.user

import app.hdj.datepick.domain.repository.MeRepository
import app.hdj.datepick.utils.Authenticator
import app.hdj.datepick.utils.Inject
import app.hdj.datepick.utils.Singleton
import kotlinx.coroutines.flow.onCompletion

@Singleton
class UnregisterMeUseCase @Inject constructor(
    private val authenticator: Authenticator,
    private val meRepository: MeRepository,
) {

    fun execute(param: Param) =
        meRepository.unregister(param.type, param.reason)
            .onCompletion { if (it == null) authenticator.signOut() }

    data class Param(
        val type: Int,
        val reason: String?
    )

}