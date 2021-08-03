package app.hdj.datepick.data.repository

import app.hdj.datepick.data.api.UserApi
import app.hdj.datepick.data.db.UserCache
import app.hdj.datepick.domain.StateData
import app.hdj.datepick.domain.StateData.Companion.loading
import app.hdj.datepick.domain.model.User
import app.hdj.datepick.domain.repository.UserRepository
import app.hdj.datepick.utils.Inject
import app.hdj.datepick.utils.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Singleton
class UserRepositoryImp @Inject constructor(
    private val userApi : UserApi,
    private val userCache: UserCache
) : UserRepository {

    override fun getMe(): Flow<StateData<User>> = flow {
        emit(loading())

    }

    override fun updateMe(user: User): Flow<StateData<User>> = flow {
        emit(loading())

    }

}