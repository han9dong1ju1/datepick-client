package app.hdj.shared.client.data.datastore

import kotlinx.coroutines.flow.StateFlow

interface AuthDataStore {

    val idToken: StateFlow<String?>

    suspend fun setIdToken(token: String)

}