package app.hdj.datepick.domain.repository

import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.model.user.User
import kotlinx.coroutines.flow.Flow

interface MeRepository {

    suspend fun cache(): User?

    fun observableCache(): Flow<User?>

    fun fetch(): Flow<LoadState<User>>

    fun update(nickname: String?, profileImageUrl: String?, gender: String?): Flow<LoadState<User>>

    fun signIn(): Flow<LoadState<User>>

    fun signOut(): Flow<LoadState<String>>

    fun register(provider : String, token : String): Flow<LoadState<Unit>>

    fun unregister(type: Int, reason: String?): Flow<LoadState<Unit>>

}