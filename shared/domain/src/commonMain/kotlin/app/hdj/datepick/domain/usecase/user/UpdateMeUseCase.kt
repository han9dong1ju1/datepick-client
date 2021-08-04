package app.hdj.datepick.domain.usecase.user

import app.hdj.datepick.domain.repository.UserRepository
import app.hdj.datepick.domain.usecase.UseCase
import app.hdj.datepick.utils.Inject
import app.hdj.datepick.utils.Singleton

@Singleton
class UpdateMeUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    fun updateAll(nickname: String, profileImageUrl: String) =
        userRepository.updateMe(nickname, profileImageUrl)

    fun updateNickname(nickname: String) =
        userRepository.updateMe(nickname, null)

    fun updateProfileImage(profileImageUrl: String) =
        userRepository.updateMe(null, profileImageUrl)

}