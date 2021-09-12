package app.hdj.datepick.data.repository

import app.hdj.datepick.data.api.UserApi
import app.hdj.datepick.data.datastore.MeDataStore
import app.hdj.datepick.data.request.UserProfileRequest
import app.hdj.datepick.data.request.UserUnregisterRequest
import app.hdj.datepick.domain.LoadState
import app.hdj.datepick.domain.emitState
import app.hdj.datepick.domain.model.user.User
import app.hdj.datepick.domain.repository.MeRepository
import app.hdj.datepick.utils.Inject
import app.hdj.datepick.utils.Singleton
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
            response.data
        }.onSuccess {
            meDataStore.save(it)
        }
    }

    override fun signIn() = flow<LoadState<User>> {
        emitState {
            val response = userApi.signIn()
            response.data
        }.onSuccess {
            meDataStore.save(it)
        }
    }

    override fun signOut() = flow {
        emitState {
            val response = userApi.signOut()
            response.data
        }.onSuccess {
            meDataStore.clearMe()
        }
    }

    override fun update(
        nickname: String?,
        profileImageUrl: String?,
        gender: String?
    ) = flow<LoadState<User>> {
        emitState {
            val request = UserProfileRequest(nickname, profileImageUrl, gender)
            val response = userApi.updateMe(request)
            response.data
        }.onSuccess {
            meDataStore.save(it)
        }
    }

    override fun register(
        nickname: String,
        profileImageUrl: String?,
        gender: String?
    ) = flow<LoadState<User>> {
        emitState {
            val request = UserProfileRequest(nickname, profileImageUrl, gender)
            val response = userApi.register(request)
            response.data
        }.onSuccess {
            meDataStore.save(it)
        }
    }

    override fun unregister(type: Int, reason: String?) =
        flow {
            emitState {
                userApi.unregister(UserUnregisterRequest(type, reason))
                meDataStore.clearMe()
            }
        }

}