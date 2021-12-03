package app.hdj.datepick.domain.usecase.user

import app.hdj.datepick.domain.repository.MeRepository
import app.hdj.datepick.domain.Authenticator
import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.isStateSucceed
import app.hdj.datepick.domain.usecase.UseCase
import app.hdj.datepick.utils.Inject
import app.hdj.datepick.utils.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

@Singleton
class UnregisterMeUseCase @Inject constructor(
    private val authenticator: Authenticator,
    private val meRepository: MeRepository,
) : UseCase<UnregisterMeUseCase.Param, Flow<LoadState<Unit>>> {

    override operator fun invoke(input: Param) =
        meRepository.unregister(input.reason)
            .onEach {
                if (it.isStateSucceed()) authenticator.signOut()
            }

    data class Param(
        val reason: String
    )

}