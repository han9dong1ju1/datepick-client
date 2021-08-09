package app.hdj.datepick.domain.usecase.user

import app.hdj.datepick.domain.StateData
import app.hdj.datepick.domain.model.user.User
import app.hdj.datepick.domain.repository.MeRepository
import app.hdj.datepick.utils.Inject
import app.hdj.datepick.utils.Singleton
import kotlinx.coroutines.flow.Flow

@Singleton
class UpdateMeUseCase @Inject constructor(
    private val meRepository: MeRepository
) {

    fun execute(parameter: UserProfileRequestParams): Flow<StateData<User>> {
        val (nickname, profileImageUrl, gender) = parameter
        return meRepository.update(nickname, profileImageUrl, gender?.value)
    }

}