package app.hdj.shared.client.data

import app.hdj.shared.client.data.datastore.AppDataStore
import app.hdj.shared.client.utils.AuthenticatorImp
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DaggerAuthenticatorImp @Inject constructor(appDataStore: AppDataStore) :
    AuthenticatorImp(appDataStore)