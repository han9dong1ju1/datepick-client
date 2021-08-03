package app.hdj.datepick.domain.repository

import app.hdj.datepick.domain.StateData
import app.hdj.datepick.domain.model.user.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getMe() : Flow<StateData<User>>

    fun updateMe(nickname : String?, profileImageUrl : String?) : Flow<StateData<User>>

}