package app.hdj.shared.client.data.cache

import app.hdj.shared.client.domain.entity.User

open class UserCache : Cache<User> {

    override suspend fun get(id: String): User? {
        TODO("Not yet implemented")
    }

    override suspend fun cache(data: User) {

    }

}