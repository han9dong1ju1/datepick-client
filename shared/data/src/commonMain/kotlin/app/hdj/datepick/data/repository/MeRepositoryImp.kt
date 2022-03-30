package app.hdj.datepick.data.repository

import app.hdj.datepick.data.api.UserApi
import app.hdj.datepick.data.datastore.MeDataStore
import app.hdj.datepick.data.request.user.UserProfileRequest
import app.hdj.datepick.data.request.user.UserRegisterRequest
import app.hdj.datepick.data.request.user.UserUnregisterRequest
import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.emitState
import app.hdj.datepick.domain.model.user.User
import app.hdj.datepick.domain.model.user.UserGender
import app.hdj.datepick.domain.repository.MeRepository
import app.hdj.datepick.utils.di.Inject
import app.hdj.datepick.utils.di.Singleton
import io.ktor.utils.io.core.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Singleton
class MeRepositoryImp @Inject constructor(
    private val userApi: UserApi,
    private val meDataStore: MeDataStore
) : MeRepository {

    override suspend fun cache(): User? = meDataStore.cachedMe()

    override fun observableCache(): Flow<User?> = meDataStore.observableMe

    override fun fetch() = flow {
        emitState(cache()) {
            val response = userApi.getMe()
            response.data.run { User(id, nickname, imageUrl, gender) }
        }.onSuccess {
            meDataStore.save(it)
        }
    }

    override fun update(
        nickname: String,
        gender: UserGender?,
        profileImageUrl: Input?,
    ) = flow {
        emitState {
            val request = UserProfileRequest(nickname, gender, profileImageUrl)
            val response = userApi.updateMe(request)
            response.data.run { User(id, nickname, imageUrl, gender) }
        }.onSuccess {
            meDataStore.save(it)
        }
    }

    override fun register(
        token: String
    ) = flow {
        emitState {
            val request = UserRegisterRequest(token)
            meDataStore.save(userApi.register(request).data.run { User(id, nickname, imageUrl, gender) })
        }
    }

    override fun unregister(reason: String) =
        flow {
            emitState {
                userApi.unregister(UserUnregisterRequest(reason))
                meDataStore.clearMe()
            }
        }

}