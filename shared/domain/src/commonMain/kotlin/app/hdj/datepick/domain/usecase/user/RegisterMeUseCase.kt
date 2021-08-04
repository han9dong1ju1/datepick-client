package app.hdj.datepick.domain.usecase.user

import app.hdj.datepick.domain.repository.UserRepository
import app.hdj.datepick.utils.Inject
import app.hdj.datepick.utils.Singleton

@Singleton
class RegisterMeUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    fun register(
        nickname: String, profileImageUrl: String
    ) = userRepository.register(nickname, profileImageUrl)

}