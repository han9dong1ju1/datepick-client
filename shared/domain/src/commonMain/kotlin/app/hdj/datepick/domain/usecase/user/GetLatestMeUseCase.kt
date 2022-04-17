package app.hdj.datepick.domain.usecase.user

import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.LoadState.Companion.failed
import app.hdj.datepick.domain.mapFailedState
import app.hdj.datepick.domain.model.user.User
import app.hdj.datepick.domain.repository.AuthRepository
import app.hdj.datepick.domain.repository.MeRepository
import app.hdj.datepick.domain.usecase.UseCase
import app.hdj.datepick.utils.di.Inject
import app.hdj.datepick.utils.di.Singleton
import io.ktor.client.plugins.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow

@Singleton
class GetLatestMeUseCase @Inject constructor(
    private val meRepository: MeRepository
) : UseCase<Unit, Flow<LoadState<User>>> {

    override operator fun invoke(input: Unit): Flow<LoadState<User>> = meRepository.fetch()

}