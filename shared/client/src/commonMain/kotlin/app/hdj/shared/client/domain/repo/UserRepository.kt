package app.hdj.shared.client.domain.repo

import app.hdj.shared.client.domain.State
import app.hdj.shared.client.domain.entity.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUser(userId : Long) : Flow<State<User>>

}