package app.hdj.datepick.domain.usecase.user

import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.LoadState.Companion.failed
import app.hdj.datepick.domain.LoadState.Companion.success
import app.hdj.datepick.domain.mapFailedState
import app.hdj.datepick.domain.model.user.User
import app.hdj.datepick.domain.repository.MeRepository
import app.hdj.datepick.utils.Authenticator
import app.hdj.datepick.utils.Inject
import app.hdj.datepick.utils.Singleton
import app.hdj.datepick.utils.exception.NotRegisteredException
import io.ktor.client.features.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

@Singleton
class GetMeUseCase @Inject constructor(
    private val meRepository: MeRepository,
    private val authenticator: Authenticator
) {

    fun fetchFromRemote(): Flow<LoadState<User>> {
        if (authenticator.idToken == null)
            return flowOf(failed(NotRegisteredException(firebaseRegistered = false)))

        return meRepository.fetch().mapFailedState { error ->
            val cached = meRepository.cache()
            val throwable = error.throwable
            if (throwable is ResponseException &&
                throwable.response.status == HttpStatusCode.NotFound
            ) {
                failed(NotRegisteredException(firebaseRegistered = true), cached)
            } else {
                failed(throwable, cached)
            }
        }
    }

    fun fetch(): Flow<LoadState<User>> {
        return  flow {
            val cached = meRepository.cache()
            if (cached != null) emit(success(cached))
            else emitAll(fetchFromRemote())
        }
    }

    fun observable() = meRepository.observableCache()

}