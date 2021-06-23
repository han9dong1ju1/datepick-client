package app.hdj.shared.client.data

import app.hdj.shared.client.data.api.UserApi
import app.hdj.shared.client.data.cache.UserCache
import app.hdj.shared.client.data.repo.UserRepositoryImp
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DaggerUserRepositoryImp @Inject constructor(userApi: UserApi, userCache: UserCache) :
    UserRepositoryImp(userApi, userCache)