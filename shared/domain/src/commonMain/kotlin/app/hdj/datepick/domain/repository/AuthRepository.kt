package app.hdj.datepick.domain.repository

import app.hdj.datepick.domain.LoadState
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun signIn(token : String, provider : String) : Flow<Unit>

    fun refreshToken(forceRefresh : Boolean) : Flow<Unit>

    fun unregister(reason : String) : Flow<Unit>

}