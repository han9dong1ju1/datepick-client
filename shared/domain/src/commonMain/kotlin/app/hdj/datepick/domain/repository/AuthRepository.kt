package app.hdj.datepick.domain.repository

import app.hdj.datepick.domain.LoadState
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun signIn(token : String, provider : String) : Flow<LoadState<Unit>>

    fun refreshToken(forceRefresh : Boolean) : Flow<LoadState<Unit>>

    fun unregister(reason : String) : Flow<LoadState<Unit>>

}