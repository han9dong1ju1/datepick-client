package app.hdj.datepick.domain.usecase.user

import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.repository.MeRepository
import app.hdj.datepick.domain.usecase.user.params.UserRegisterRequestParams
import app.hdj.datepick.utils.Inject
import app.hdj.datepick.utils.Singleton
import kotlinx.coroutines.flow.Flow

@Singleton
class RegisterMeUseCase @Inject constructor(
    private val meRepository: MeRepository
) {

    fun execute(param: UserRegisterRequestParams): Flow<LoadState<Unit>> {
        val (provider, token) = param
        return meRepository.register(provider, token)
    }

}