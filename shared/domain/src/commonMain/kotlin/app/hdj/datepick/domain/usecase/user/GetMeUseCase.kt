package app.hdj.datepick.domain.usecase.user

import app.hdj.datepick.domain.repository.UserRepository
import app.hdj.datepick.utils.Inject
import app.hdj.datepick.utils.Singleton

@Singleton
class GetMeUseCase @Inject constructor(private val userRepository: UserRepository) {

    operator fun invoke() = userRepository.getMe()

}