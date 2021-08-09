package app.hdj.datepick.domain.usecase.user

import app.hdj.datepick.domain.StateData
import app.hdj.datepick.domain.model.user.User
import app.hdj.datepick.domain.repository.MeRepository
import app.hdj.datepick.utils.Inject
import app.hdj.datepick.utils.Singleton
import kotlinx.coroutines.flow.Flow

@Singleton
class RegisterMeUseCase @Inject constructor(
    private val meRepository: MeRepository
) {

    fun execute(param: RegisterMeProfileParameter): Flow<StateData<User>> {
        val (nickname, profileImageUrl) = param
        return meRepository.register(nickname, profileImageUrl)
    }

}


data class RegisterMeProfileParameter(
    val nickname: String,
    val profileImageUrl: String? = null
)