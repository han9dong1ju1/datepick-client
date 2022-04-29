package app.hdj.datepick.android.ui.dialog

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.android.R
import app.hdj.datepick.android.utils.extract
import app.hdj.datepick.presentation.login.LoginBottomSheetDialogViewModel
import app.hdj.datepick.presentation.login.LoginBottomSheetDialogViewModelDelegate
import app.hdj.datepick.presentation.login.LoginBottomSheetDialogViewModelDelegate.Effect.SignInSucceed
import app.hdj.datepick.presentation.login.LoginBottomSheetDialogViewModelDelegate.Event.RequestGoogleLogin
import app.hdj.datepick.ui.components.BaseButton
import app.hdj.datepick.ui.utils.collectInLaunchedEffect
import app.hdj.datepick.ui.utils.getActivity
import app.hdj.datepick.utils.PlatformLogger
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.spec.DestinationStyle

@Composable
@Destination(style = DestinationStyle.BottomSheet::class)
fun LoginBottomSheetDialog(
    navigator: DestinationsNavigator
) {
    LoginBottomSheetDialogContent(
        vm = hiltViewModel<LoginBottomSheetDialogViewModel>(),
        dismiss = { navigator.popBackStack() }
    )
}

@Composable
private fun LoginBottomSheetDialogContent(
    vm: LoginBottomSheetDialogViewModelDelegate,
    dismiss: () -> Unit
) {

    val (state, effect, event) = vm.extract()

    effect.collectInLaunchedEffect {
        if (it is SignInSucceed) {
            dismiss()
        }
    }

    val context = LocalContext.current
    val activity = context.getActivity()

    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intent = result.data
                if (result.data != null) {
                    val task = GoogleSignIn.getSignedInAccountFromIntent(intent)
                    task
                        .addOnFailureListener { PlatformLogger.e(it) }
                        .addOnSuccessListener {
                            val serverAuthCode = it.serverAuthCode
                            if (serverAuthCode != null) {
                                event(RequestGoogleLogin(serverAuthCode))
                            }
                        }
                }
            }
        }

    Surface(
        modifier = Modifier.fillMaxWidth()
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {

            BaseButton(
                modifier = Modifier.fillMaxWidth(),
                text = "Google 로 시작"
            ) {
                val googleSignInClient = activity?.let {
                    GoogleSignIn.getClient(
                        it,
                        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                            .requestEmail()
                            .requestIdToken(context.getString(R.string.gcp_android_client_id))
                            .requestId()
                            .requestServerAuthCode(context.getString(R.string.gcp_dev_server_client_id))
                            .requestProfile()
                            .build()
                    ).signInIntent
                }

                launcher.launch(googleSignInClient)
            }

        }

    }

}
