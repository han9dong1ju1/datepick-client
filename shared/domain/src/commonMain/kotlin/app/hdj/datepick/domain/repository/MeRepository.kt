package app.hdj.datepick.domain.repository

import app.hdj.datepick.domain.StateData
import app.hdj.datepick.domain.model.user.User
import kotlinx.coroutines.flow.Flow

interface MeRepository {

    fun cached() : User?

    fun fetch() : Flow<StateData<User>>

    fun update(nickname : String?, profileImageUrl : String?) : Flow<StateData<User>>

    fun unregister() : Flow<StateData<Unit>>

}