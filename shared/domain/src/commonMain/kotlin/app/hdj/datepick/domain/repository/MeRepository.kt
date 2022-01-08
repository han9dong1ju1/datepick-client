package app.hdj.datepick.domain.repository

import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.model.user.User
import app.hdj.datepick.domain.model.user.UserGender
import io.ktor.utils.io.core.*
import kotlinx.coroutines.flow.Flow

interface MeRepository {

    suspend fun cache(): User?

    fun observableCache(): Flow<User?>

    fun fetch(): Flow<LoadState<User>>

    fun update(
        nickname: String,
        gender: UserGender?,
        profileImageUrl: Input?,
    ): Flow<LoadState<User>>

    fun register(provider: String, token: String): Flow<LoadState<Unit>>

    fun unregister(reason: String): Flow<LoadState<Unit>>

}