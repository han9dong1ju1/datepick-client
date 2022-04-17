package app.hdj.datepick.data.repository

import app.hdj.datepick.data.model.request.auth.AuthRefreshTokenRequest
import app.hdj.datepick.data.remote.api.AuthApi
import app.hdj.datepick.data.utils.AuthTokenManager
import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.LoadState.Companion.failed
import app.hdj.datepick.domain.LoadState.Companion.loading
import app.hdj.datepick.domain.LoadState.Companion.success
import app.hdj.datepick.domain.repository.AuthRepository
import app.hdj.datepick.utils.di.Inject
import app.hdj.datepick.utils.di.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Singleton
class AuthRepositoryImp @Inject constructor(
    private val authApi: AuthApi,
    private val authTokenManager: AuthTokenManager
) : AuthRepository {

    override fun signIn(token: String, provider: String) = flow<LoadState<Unit>> {
        emit(loading())
        authApi.runCatching { signIn(token, provider) }
            .onFailure { emit(failed(it)) }
            .onSuccess { emit(success(Unit)) }
    }

    override fun refreshToken(forceRefresh: Boolean) = flow<LoadState<Unit>> {
        val refreshToken = authTokenManager.getRefreshToken()
        if ((!authTokenManager.isTokenExpired() && !forceRefresh) || refreshToken == null) {
            emit(success(Unit))
        } else {
            emit(loading())
            authApi.runCatching { refreshToken(AuthRefreshTokenRequest(refreshToken = refreshToken)) }
                .onFailure { emit(failed(it)) }
                .onSuccess { emit(success(Unit)) }
        }
    }

    override fun unregister(reason: String): Flow<LoadState<Unit>> {
        TODO("Not yet implemented")
    }

}