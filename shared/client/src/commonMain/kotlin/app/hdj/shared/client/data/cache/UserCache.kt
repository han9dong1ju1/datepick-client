package app.hdj.shared.client.data.cache

import app.hdj.shared.client.domain.entity.User

open class UserCache : Cache<User> {

    override suspend fun get(id: String): User? {
        TODO("Not yet implemented")
    }

    suspend fun getMe() : User? {
        return null
    }

    override suspend fun cache(data: User) {

    }

}