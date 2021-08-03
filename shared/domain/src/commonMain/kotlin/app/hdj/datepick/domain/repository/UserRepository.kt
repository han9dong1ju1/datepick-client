package app.hdj.datepick.domain.repository

import app.hdj.datepick.domain.StateData
import app.hdj.datepick.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getMe() : Flow<StateData<User>>

    fun updateMe(user: User) : Flow<StateData<User>>

}