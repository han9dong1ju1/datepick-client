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

    override fun cached(): User? = meDataStore.me

    override fun fetch() = flow<StateData<User>> {
        emitState {
            userApi.getMe().apply { meDataStore.save(this) }
        }
    }

    override fun update(nickname: String?, profileImageUrl: String?) = flow<StateData<User>> {
        emitState {
            val request = UserProfileRequest(nickname, profileImageUrl)
            userApi.updateMe(request).apply { meDataStore.save(this) }
        }
    }

    override fun register() = flow<StateData<User>> {
        emitState {
            userApi.register().apply { meDataStore.save(this) }
        }
    }

    override fun unregister() = flow {
        emitState {
            userApi.unregister()
            meDataStore.delete()
        }
    }

}