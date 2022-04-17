package app.hdj.datepick.domain.usecase.auth

import app.hdj.datepick.domain.repository.AuthRepository
import app.hdj.datepick.utils.di.Inject
import app.hdj.datepick.utils.di.Singleton

@Singleton
class RefreshTokenUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    operator fun invoke(forceRefresh: Boolean) = authRepository.refreshToken(forceRefresh)

}