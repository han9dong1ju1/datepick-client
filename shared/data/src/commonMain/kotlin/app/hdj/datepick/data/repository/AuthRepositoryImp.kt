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
import app.hdj.datepick.utils.di.Named
import app.hdj.datepick.utils.di.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Singleton
class AuthRepositoryImp @Inject constructor(
    @Named("mocked") private val authApi: AuthApi,
    private val authTokenManager: AuthTokenManager
) : AuthRepository {

    override fun signIn(token: String, provider: String) = flow {
        val response = authApi.signIn(token, provider)
        authTokenManager.setToken(response.data)
        emit(Unit)
    }

    override fun refreshToken(forceRefresh: Boolean) = flow {
        val refreshToken = authTokenManager.getRefreshToken()
        if ((!authTokenManager.isTokenExpired() && !forceRefresh) || refreshToken == null) {
            emit(Unit)
        } else {
            val response = authApi.refreshToken(AuthRefreshTokenRequest(refreshToken))
            authTokenManager.setToken(response.data)
            emit(Unit)
        }
    }

    override fun unregister(reason: String): Flow<Unit> {
        TODO("Not yet implemented")
    }

}