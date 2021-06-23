package app.hdj.shared.client.data

import app.hdj.shared.client.data.repo.SettingRepositoryImp
import app.hdj.shared.client.utils.LocalDataStore
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DaggerSettingRepositoryImp @Inject constructor(
    localDataStore: LocalDataStore
) : SettingRepositoryImp(localDataStore)