package app.hdj.datepick.android.ui.dialog.login

import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import app.hdj.datepick.android.R
import app.hdj.datepick.utils.PlatformLogger
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.GoogleAuthProvider

class GoogleSignInContract : ActivityResultContract<Unit, AuthCredential?>() {

    private val Context.googleSignInOptions get() =
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

    override fun createIntent(context: Context, input: Unit): Intent {
        val signInClient = GoogleSignIn.getClient(context, context.googleSignInOptions)
        return signInClient.signInIntent
    }

    override fun parseResult(resultCode: Int, intent: Intent?): AuthCredential? {
        return runCatching {
            val account = GoogleSignIn.getSignedInAccountFromIntent(intent).result
            GoogleAuthProvider.getCredential(account.idToken, null)
        }.onFailure { PlatformLogger.e(it) }.getOrNull()
    }
}