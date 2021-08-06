package app.hdj.datepick.utils

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.AuthCredential
import dev.gitlive.firebase.auth.FirebaseUser
import dev.gitlive.firebase.auth.auth

@Singleton
class FirebaseAuthenticator @Inject constructor() {

    private var _idToken : String? = null
    val idToken get() = _idToken

    suspend fun getCurrentFirebaseUser() : FirebaseUser? {
        val user = Firebase.auth.currentUser
        _idToken = user?.getIdToken(false)
        return user
    }

    suspend fun signInGoogle(credential: AuthCredential) {
        val authResult = Firebase.auth.signInWithCredential(credential)
        _idToken = authResult.user?.getIdToken(false)
    }

}