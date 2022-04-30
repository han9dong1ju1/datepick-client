package app.hdj.datepick.domain.repository

import app.hdj.datepick.domain.model.user.User
import app.hdj.datepick.domain.model.user.UserGender
import io.ktor.utils.io.core.*
import kotlinx.coroutines.flow.Flow

interface MeRepository {

    suspend fun cache(): User?

    fun observableCache(): Flow<User?>

    fun fetch(): Flow<User>

    fun update(
        nickname: String,
        gender: UserGender?,
        profileImageUrl: Input?,
    ): Flow<User>

    suspend fun removeCache()

}