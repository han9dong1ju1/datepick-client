package app.hdj.datepick.domain.usecase.user

import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.model.user.User
import app.hdj.datepick.domain.repository.MeRepository
import app.hdj.datepick.utils.Inject
import app.hdj.datepick.utils.Singleton
import kotlinx.coroutines.flow.Flow

@Singleton
class RegisterMeUseCase @Inject constructor(
    private val meRepository: MeRepository
) {

    fun execute(param: UserProfileRequestParams): Flow<LoadState<User>> {
        val (nickname, profileImageUrl, gender) = param

        if (nickname == null || gender == null)
            throw IllegalArgumentException("Nickname 과 Gender 는 Null 이 될 수 없습니다.")

        return meRepository.register(nickname, profileImageUrl, gender.value)
    }

}