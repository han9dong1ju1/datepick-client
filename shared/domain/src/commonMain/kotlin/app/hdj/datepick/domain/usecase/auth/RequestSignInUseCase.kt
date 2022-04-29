package app.hdj.datepick.domain.usecase.auth

import app.hdj.datepick.domain.repository.AuthRepository
import app.hdj.datepick.domain.repository.MeRepository
import app.hdj.datepick.domain.usecase.auth.params.SignInRequest
import app.hdj.datepick.utils.di.Inject
import app.hdj.datepick.utils.di.Singleton
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.flatMapConcat

@OptIn(FlowPreview::class)
@Singleton
class RequestSignInUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val meRepository: MeRepository
) {

    operator fun invoke(input: SignInRequest) =
        authRepository
            .signIn(input.code, input.provider.value)
            .flatMapConcat { meRepository.fetch() }

}