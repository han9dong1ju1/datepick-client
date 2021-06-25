package app.hdj.shared.client.data.repo

import app.hdj.shared.client.data.datastore.AppDataStore
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DaggerSettingRepositoryImp @Inject constructor(appDataStore: AppDataStore) :
    SettingRepositoryImp(appDataStore)