package app.hdj.shared.client.data.api

import app.hdj.shared.client.data.datastore.AppDataStore
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DaggerAuthenticatorImp @Inject constructor(appDataStore: AppDataStore) :
    AuthenticatorImp(appDataStore)