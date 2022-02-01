package app.hdj.datepick.domain.usecase.user

import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.model.user.User
import app.hdj.datepick.domain.repository.MeRepository
import app.hdj.datepick.domain.usecase.user.params.UserProfileRequestParams
import app.hdj.datepick.utils.di.Inject
import app.hdj.datepick.utils.di.Singleton
import kotlinx.coroutines.flow.Flow

@Singleton
class UpdateMeUseCase @Inject constructor(
    private val meRepository: MeRepository
) {

    operator fun invoke(parameter: UserProfileRequestParams): Flow<LoadState<User>> {
        val (nickname, gender, profileImageUrl) = parameter
        return meRepository.update(nickname, gender, profileImageUrl)
    }

}