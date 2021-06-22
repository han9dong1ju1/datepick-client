package app.hdj.shared.client

import app.hdj.shared.client.data.api.UserApi
import app.hdj.shared.client.data.cache.UserCache
import app.hdj.shared.client.data.repo.UserRepositoryImp

class DaggerUserRepositoryImp @Inject constructor(
    private val userApi: UserApi,
    private val userCache: UserCache
) : UserRepositoryImp(userApi, userCache) {
}