package app.hdj.datepick.domain.usecase.auth

import app.hdj.datepick.domain.repository.MeRepository
import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.isStateSucceed
import app.hdj.datepick.domain.repository.AuthRepository
import app.hdj.datepick.domain.usecase.UseCase
import app.hdj.datepick.domain.usecase.auth.params.UnregisterRequest
import app.hdj.datepick.utils.di.Inject
import app.hdj.datepick.utils.di.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

@Singleton
class UnregisterMeUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val meRepository: MeRepository,
) : UseCase<UnregisterRequest, Flow<LoadState<Unit>>> {

    override operator fun invoke(input: UnregisterRequest) =
        authRepository.unregister(input.reason)
            .onEach { if (it.isStateSucceed()) meRepository.removeCache() }

}