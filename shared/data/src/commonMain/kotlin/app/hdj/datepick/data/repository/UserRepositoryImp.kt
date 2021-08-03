package app.hdj.datepick.data.repository

import app.hdj.datepick.UserTable
import app.hdj.datepick.data.api.UserApi
import app.hdj.datepick.data.db.UserCache
import app.hdj.datepick.data.entity.UserResponse
import app.hdj.datepick.data.mapper.Mapper
import app.hdj.datepick.data.mapper.UserMapper
import app.hdj.datepick.domain.StateData
import app.hdj.datepick.domain.StateData.Companion.loading
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

    override fun getMe(): Flow<StateData<User>> = flow {
        emit(loading())

    }

    override fun updateMe(nickname: String?, profileImageUrl: String?): Flow<StateData<User>> =
        flow {
            emit(loading())


        }

}