package app.hdj.datepick.domain.usecase.user

import app.hdj.datepick.domain.model.user.User
import app.hdj.datepick.domain.repository.MeRepository
import app.hdj.datepick.domain.usecase.UseCase
import app.hdj.datepick.utils.Inject
import app.hdj.datepick.utils.Singleton
import kotlinx.coroutines.flow.Flow

@Singleton
class ObserveMeUseCase @Inject constructor(
    private val meRepository: MeRepository
) : UseCase<Unit, Flow<User?>> {

    override operator fun invoke(input : Unit) = meRepository.observableCache()

}