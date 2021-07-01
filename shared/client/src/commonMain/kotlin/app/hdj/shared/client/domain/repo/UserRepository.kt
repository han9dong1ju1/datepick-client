package app.hdj.shared.client.domain.repo

import app.hdj.shared.client.domain.StateData
import app.hdj.shared.client.domain.entity.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUser(userId : String) : Flow<StateData<User>>

    fun getMe() : Flow<StateData<User>>

    fun updateFirebaseToken(token : String) : Flow<StateData<String>>

}