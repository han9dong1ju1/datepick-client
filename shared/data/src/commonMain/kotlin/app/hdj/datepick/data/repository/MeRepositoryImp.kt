package app.hdj.datepick.data.repository

import app.hdj.datepick.data.api.UserApi
import app.hdj.datepick.data.datastore.MeDataStore
import app.hdj.datepick.data.request.UserProfileRequest
import app.hdj.datepick.domain.StateData
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
        emitState(defaultValue = cache(), onSuccess = meDataStore::save) {
            val response = userApi.getMe()
            requireNotNull(response.data)
        }
    }

    override fun update(nickname: String?, profileImageUrl: String?) = flow<StateData<User>> {
        emitState(onSuccess = meDataStore::save) {
            val request = UserProfileRequest(nickname, profileImageUrl)
            val response = userApi.updateMe(request)
            requireNotNull(response.data)
        }
    }

    override fun register(nickname: String, profileImageUrl: String?) = flow<StateData<User>> {
        emitState(onSuccess = meDataStore::save) {
            val request = UserProfileRequest(nickname, profileImageUrl)
            val response = userApi.register(request)
            requireNotNull(response.data)
        }
    }

    override fun unregister() = flow {
        emitState {
            userApi.unregister()
            meDataStore.clearMe()
        }
    }

}