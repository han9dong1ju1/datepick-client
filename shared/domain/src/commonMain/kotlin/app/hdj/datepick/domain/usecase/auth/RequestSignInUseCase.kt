package app.hdj.datepick.domain.usecase.auth

import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.isStateFailed
import app.hdj.datepick.domain.isStateLoading
import app.hdj.datepick.domain.isStateSucceed
import app.hdj.datepick.domain.repository.AuthRepository
import app.hdj.datepick.domain.repository.MeRepository
import app.hdj.datepick.domain.usecase.auth.params.SignInRequest
import app.hdj.datepick.utils.di.Inject
import app.hdj.datepick.utils.di.Singleton
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

@OptIn(FlowPreview::class)
@Singleton
class RequestSignInUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val meRepository: MeRepository
) {

    operator fun invoke(input: SignInRequest) =
        authRepository
            .signIn(input.token, input.provider.value)
            .flatMapConcat { meRepository.fetch() }

}