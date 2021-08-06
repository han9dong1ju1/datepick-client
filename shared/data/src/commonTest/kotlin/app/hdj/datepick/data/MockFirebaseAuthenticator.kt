package app.hdj.datepick.data

import app.hdj.datepick.utils.Authenticator
import app.hdj.datepick.utils.TestConstants
import dev.gitlive.firebase.auth.AuthCredential
import dev.gitlive.firebase.auth.FirebaseUser

class MockFirebaseAuthenticator : Authenticator {

    override val idToken: String get() = TestConstants.ID_TOKEN

    override suspend fun getCurrentFirebaseUser(): FirebaseUser? {
        throw UnsupportedOperationException("Not Supported in Test")
    }

    override suspend fun signInGoogle(credential: AuthCredential) {
        throw UnsupportedOperationException("Not Supported in Test")
    }

}