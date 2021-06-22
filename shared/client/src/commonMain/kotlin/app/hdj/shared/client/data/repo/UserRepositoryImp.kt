package app.hdj.shared.client.data.repo

import app.hdj.shared.client.data.ApiResponse
import app.hdj.shared.client.data.api.UserApi
import app.hdj.shared.client.data.cache.UserCache
import app.hdj.shared.client.domain.State
import app.hdj.shared.client.domain.entity.User
import app.hdj.shared.client.domain.repo.UserRepository
import kotlinx.coroutines.flow.flow

open class UserRepositoryImp(
    private val userApi: UserApi,
    private val userCache: UserCache
) : UserRepository {

    override fun getUser(userId: Long) = flow<State<User>> {
        emit(State.Loading())

        userApi
            .runCatching { getUser(userId) }
            .onSuccess {
                val user = it.data

                emit(State.Success(it.data))
            }.onFailure {
                emit(State.Failed(it))
            }
    }

}