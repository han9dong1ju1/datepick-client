package app.hdj.datepick.android.ui.components.dialog.login

import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.GoogleAuthProvider

class GoogleSignInContract : ActivityResultContract<Unit, AuthCredential?>() {

    private val googleSignInOptions =
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

    override fun createIntent(context: Context, input: Unit?): Intent {
        val signInClient = GoogleSignIn.getClient(context, googleSignInOptions)
        return signInClient.signInIntent
    }

    override fun parseResult(resultCode: Int, intent: Intent?): AuthCredential? {
        return runCatching {
            val account = GoogleSignIn.getSignedInAccountFromIntent(intent).result
            GoogleAuthProvider.getCredential(account.idToken, null)
        }.getOrNull()
    }
}