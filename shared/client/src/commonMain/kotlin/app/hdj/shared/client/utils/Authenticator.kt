package app.hdj.shared.client.utils

import app.hdj.shared.client.data.datastore.AuthDataStore
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.AuthCredential
import dev.gitlive.firebase.auth.FirebaseUser
import dev.gitlive.firebase.auth.auth

interface Authenticator {

    suspend fun currentUser(): FirebaseUser?
    suspend fun signIn(authCredential: AuthCredential): FirebaseUser?

}

open class AuthenticatorImp(private val authDataStore: AuthDataStore) : Authenticator {

    override suspend fun currentUser(): FirebaseUser? {
        val user = Firebase.auth.currentUser
        user?.getIdToken(false)?.let { authDataStore.setIdToken(it) }
        return user
    }

    override suspend fun signIn(authCredential: AuthCredential): FirebaseUser? {
        val result = Firebase.auth.signInWithCredential(authCredential)
        val user = result.user
        user?.getIdToken(false)?.let { authDataStore.setIdToken(it) }
        return user
    }

}