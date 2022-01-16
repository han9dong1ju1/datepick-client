package app.hdj.datepick.data

import app.hdj.datepick.domain.Authenticator
import app.hdj.datepick.utils.TestConstants
import dev.gitlive.firebase.auth.AuthCredential
import dev.gitlive.firebase.auth.FirebaseUser

class MockFirebaseAuthenticator : Authenticator {

    override val idToken: String get() = TestConstants.ID_TOKEN

    override suspend fun getCurrentFirebaseUser(): FirebaseUser? {
        throw UnsupportedOperationException("Not Supported in Test")
    }

    override suspend fun signInWithCredential(credential: AuthCredential) {
        throw UnsupportedOperationException("Not Supported in Test")
    }

    override suspend fun signInCustomToken(customToken: String) {

    }

    override suspend fun signOut() {

    }

    override suspend fun refreshIdToken() {

    }

}