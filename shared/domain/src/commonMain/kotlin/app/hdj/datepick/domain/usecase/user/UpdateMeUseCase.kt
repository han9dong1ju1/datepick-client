package app.hdj.datepick.domain.usecase.user

import app.hdj.datepick.domain.model.User
import app.hdj.datepick.domain.repository.UserRepository
import app.hdj.datepick.utils.Inject
import app.hdj.datepick.utils.Singleton

@Singleton
class UpdateMeUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    private fun updateMe(user: User) = userRepository.updateMe(user)

}