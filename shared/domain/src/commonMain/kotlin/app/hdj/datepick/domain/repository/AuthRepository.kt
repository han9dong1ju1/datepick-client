package app.hdj.datepick.domain.repository

import app.hdj.datepick.domain.model.auth.RefreshTokenResult
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun signIn(token : String, provider : String) : Flow<Unit>

    fun refreshToken(forceRefresh : Boolean) : Flow<RefreshTokenResult>

    fun unregister(reason : String) : Flow<Unit>

}