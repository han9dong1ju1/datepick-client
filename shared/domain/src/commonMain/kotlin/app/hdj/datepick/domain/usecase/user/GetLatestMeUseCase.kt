package app.hdj.datepick.domain.usecase.user

import app.hdj.datepick.domain.Authenticator
import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.LoadState.Companion.failed
import app.hdj.datepick.domain.mapFailedState
import app.hdj.datepick.domain.model.user.User
import app.hdj.datepick.domain.repository.MeRepository
import app.hdj.datepick.domain.usecase.UseCase
import app.hdj.datepick.utils.Inject
import app.hdj.datepick.utils.Singleton
import io.ktor.client.plugins.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow

@Singleton
class GetLatestMeUseCase @Inject constructor(
    private val meRepository: MeRepository,
    private val authenticator: Authenticator
) : UseCase<Unit, Flow<LoadState<User>>> {

    override operator fun invoke(input : Unit): Flow<LoadState<User>> {
        return meRepository.fetch().mapFailedState { error ->
            val cached = meRepository.cache()
            val throwable = error.throwable
            if (throwable is ResponseException &&
                throwable.response.status == HttpStatusCode.Unauthorized
            ) {
                authenticator.signOut()
            }

            failed(throwable, cached)
        }
    }

}