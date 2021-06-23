package app.hdj.shared.client.data.repo

import app.hdj.shared.client.data.api.UserApi
import app.hdj.shared.client.data.cache.UserCache
import app.hdj.shared.client.domain.StateData
import app.hdj.shared.client.domain.repo.UserRepository
import kotlinx.coroutines.flow.flow

open class UserRepositoryImp(
    private val userApi: UserApi,
    private val userCache: UserCache
) : UserRepository {

    override fun getUser(userId: String) = flow {
        emit(StateData.Loading())
        userApi
            .runCatching { getUser(userId) }
            .onSuccess {
                val user = it.data
                userCache.cache(user)
                emit(StateData.Success(it.data))
            }.onFailure {
                emit(StateData.Failed(userCache.get(userId), it))
            }
    }

}