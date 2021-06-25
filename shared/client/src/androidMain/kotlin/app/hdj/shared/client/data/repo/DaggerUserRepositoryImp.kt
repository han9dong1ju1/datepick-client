package app.hdj.shared.client.data.repo

import app.hdj.shared.client.data.api.UserApi
import app.hdj.shared.client.data.cache.UserCache
import app.hdj.shared.client.data.repo.UserRepositoryImp
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DaggerUserRepositoryImp @Inject constructor(userCache: UserCache, userApi: UserApi) :
    UserRepositoryImp(userCache, userApi)