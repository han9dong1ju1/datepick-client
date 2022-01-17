package app.hdj.datepick.data.utils

import app.hdj.datepick.domain.Authenticator
import app.hdj.datepick.utils.Inject
import app.hdj.datepick.utils.PlatformLogger
import app.hdj.datepick.utils.Singleton
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.AuthCredential
import dev.gitlive.firebase.auth.AuthResult
import dev.gitlive.firebase.auth.FirebaseUser
import dev.gitlive.firebase.auth.auth

@Singleton
class FirebaseAuthenticator @Inject constructor() : Authenticator {

    private var _idToken: String? = null
    override val idToken get() = _idToken

    override suspend fun getCurrentFirebaseUser(): FirebaseUser? {
        val user = Firebase.auth.currentUser
        _idToken = user?.getIdToken(false)
        return user
    }

    override suspend fun signInWithCredential(credential: AuthCredential) {
        val authResult = Firebase.auth.signInWithCredential(credential)
        authResult.updateIdToken(false)
    }

    override suspend fun signInCustomToken(customToken: String) {
        val authResult = Firebase.auth.signInWithCustomToken(customToken)
        authResult.updateIdToken(false)
    }

    override suspend fun signOut() {
        Firebase.auth.signOut()
        _idToken = null
    }

    private suspend fun AuthResult.updateIdToken(refresh: Boolean) {
        _idToken = user?.getIdToken(refresh)
        PlatformLogger.d("FirebaseAuthenticator : Id Token $idToken")
    }

    override suspend fun refreshIdToken() {
        _idToken = Firebase.auth.currentUser?.getIdToken(true)
    }

}