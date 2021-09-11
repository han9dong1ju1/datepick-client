package app.hdj.datepick.utils

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.AuthCredential
import dev.gitlive.firebase.auth.FirebaseUser
import dev.gitlive.firebase.auth.auth

interface Authenticator {
    val idToken: String?
    suspend fun getCurrentFirebaseUser(): FirebaseUser?
    suspend fun signInGoogle(credential: AuthCredential)
    suspend fun signInCustomToken(customToken : String)
    suspend fun signOut()
}

@Singleton
class FirebaseAuthenticator @Inject constructor() : Authenticator {

    private var _idToken: String? = null
    override val idToken get() = _idToken

    override suspend fun getCurrentFirebaseUser(): FirebaseUser? {
        val user = Firebase.auth.currentUser
        _idToken = user?.getIdToken(false)
        return user
    }

    override suspend fun signInGoogle(credential: AuthCredential) {
        val authResult = Firebase.auth.signInWithCredential(credential)
        _idToken = authResult.user?.getIdToken(false)
    }

    override suspend fun signInCustomToken(customToken: String) {
        val authResult = Firebase.auth.signInWithCustomToken(customToken)
        _idToken = authResult.user?.getIdToken(false)
    }

    override suspend fun signOut() {
        Firebase.auth.signOut()
    }

}