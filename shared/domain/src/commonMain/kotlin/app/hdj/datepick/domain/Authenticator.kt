package app.hdj.datepick.domain

import dev.gitlive.firebase.auth.AuthCredential
import dev.gitlive.firebase.auth.FirebaseUser

interface Authenticator {
    val idToken: String?
    suspend fun getCurrentFirebaseUser(): FirebaseUser?
    suspend fun signInWithCredential(credential: AuthCredential)
    suspend fun signInCustomToken(customToken: String)
    suspend fun signOut()

    suspend fun refreshIdToken()
}
