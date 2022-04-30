package app.hdj.datepick.data.repository

import app.hdj.datepick.data.model.request.user.UserProfileRequest
import app.hdj.datepick.data.remote.api.UserApi
import app.hdj.datepick.data.storage.datastore.MeDataStore
import app.hdj.datepick.domain.model.user.User
import app.hdj.datepick.domain.model.user.UserGender
import app.hdj.datepick.domain.repository.MeRepository
import app.hdj.datepick.utils.di.Inject
import app.hdj.datepick.utils.di.Named
import app.hdj.datepick.utils.di.Singleton
import io.ktor.utils.io.core.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Singleton
class MeRepositoryImp @Inject constructor(
    @Named("real") private val userApi: UserApi,
    private val meDataStore: MeDataStore
) : MeRepository {

    override suspend fun cache(): User? = meDataStore.cachedMe()

    override fun observableCache(): Flow<User?> = meDataStore.observableMe

    override fun fetch() = flow {
        val response = userApi.getMe()
        val user = response.data.run {
            User(id, nickname, imageUrl,
                gender?.let { UserGender.valueOf(it) })
        }
        meDataStore.save(user)
        emit(user)
    }

    override fun update(
        nickname: String,
        gender: UserGender?,
        profileImageUrl: Input?,
    ) = flow {
        val request = UserProfileRequest(nickname, gender, profileImageUrl)
        val response = userApi.updateMe(request)
        val user = response.data.run { User(id, nickname, imageUrl, gender) }
        meDataStore.save(user)
        emit(user)
    }

    override suspend fun removeCache() {
        meDataStore.clearMe()
    }

}