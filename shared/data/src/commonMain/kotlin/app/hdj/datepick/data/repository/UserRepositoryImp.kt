package app.hdj.datepick.data.repository

import app.hdj.datepick.UserTable
import app.hdj.datepick.data.api.UserApi
import app.hdj.datepick.data.db.UserCache
import app.hdj.datepick.data.mapper.Mapper
import app.hdj.datepick.data.mapper.UserMapper
import app.hdj.datepick.data.request.UserUpdateRequest
import app.hdj.datepick.domain.StateData
import app.hdj.datepick.domain.StateData.Companion.failed
import app.hdj.datepick.domain.StateData.Companion.loading
import app.hdj.datepick.domain.StateData.Companion.success
import app.hdj.datepick.domain.model.user.User
import app.hdj.datepick.domain.repository.UserRepository
import app.hdj.datepick.utils.Inject
import app.hdj.datepick.utils.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Singleton
class UserRepositoryImp @Inject constructor(
    private val userApi: UserApi,
    private val userCache: UserCache
) : UserRepository, Mapper<UserTable, User> by UserMapper {

    override fun getMe() = flow<StateData<User>> {
        emit(loading())
        userApi
            .runCatching { getMe() }
            .onFailure { emit(failed(it)) }
            .onSuccess {
                val me = it.asMe()
                userCache.save(me.asTable())
                emit(success(me))
            }
    }

    override fun updateMe(nickname: String?, profileImageUrl: String?) = flow<StateData<User>> {
        emit(loading())
        userApi
            .runCatching { updateMe(UserUpdateRequest(nickname, profileImageUrl)) }
            .onFailure { emit(failed(it)) }
            .onSuccess {
                userCache.save(it.asTable())
                emit(success(it))
            }
    }

    override fun unregisterMe() = flow<StateData<Unit>> {
        emit(loading())
        userApi
            .runCatching { unregisterMe() }
            .onFailure { emit(failed(it)) }
            .onSuccess {
                userCache.deleteMe()
                emit(success(Unit))
            }
    }

}