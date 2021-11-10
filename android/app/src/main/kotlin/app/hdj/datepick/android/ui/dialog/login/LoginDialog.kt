package app.hdj.datepick.android.ui.dialog.login

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import app.hdj.datepick.android.ui.icons.DatePickIcons
import app.hdj.datepick.android.ui.icons.Google
import app.hdj.datepick.android.ui.icons.Kakao
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import app.hdj.datepick.android.ui.providers.LocalToastPresenter
import app.hdj.datepick.android.ui.screens.AppNavigationGraph
import app.hdj.datepick.ui.components.BaseButton
import app.hdj.datepick.ui.components.UnAccentButton
import app.hdj.datepick.ui.styles.BaseTheme
import app.hdj.datepick.ui.utils.collectInLaunchedEffect
import app.hdj.datepick.ui.utils.extract
import com.google.accompanist.insets.navigationBarsHeight
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.bottomSheet
import dev.gitlive.firebase.auth.AuthCredential

@OptIn(ExperimentalMaterialNavigationApi::class)
fun NavGraphBuilder.loginDialog() {

    bottomSheet(AppNavigationGraph.LoginDialog.route) {

        val navController = LocalAppNavController.current
        val toastPresenter = LocalToastPresenter.current

        val vm = hiltViewModel<LoginViewModel>()
        val (state, effect, event) = vm.extract()

        effect.collectInLaunchedEffect {

        }

        val googleSignInRequest = rememberLauncherForActivityResult(GoogleSignInContract()) {
            if (it != null) event(LoginViewModelDelegate.Event.RequestSignIn(AuthCredential(it)))
        }

        Surface(
            modifier = Modifier.fillMaxWidth(), shape =
            RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {

                Spacer(modifier = Modifier.height(10.dp))


                Text(text = "로그인", style = MaterialTheme.typography.headlineSmall)

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "더 많은 기능을 사용하기 위해 로그인해주세요.",
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(40.dp))

                BaseButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    border = BorderStroke(
                        1.dp,
                        MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)
                    ),
                    text = "구글로 시작하기",
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                        contentColor = MaterialTheme.colorScheme.onSurface
                    ),
                    icon = DatePickIcons.Google,
                    iconTint = Color.Unspecified
                ) {
                    googleSignInRequest.launch(Unit)
                }

                Spacer(modifier = Modifier.height(10.dp))

                BaseButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    text = "카카오로 시작하기",
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFEE500),
                        contentColor = Color(0xD9000000),
                    ),
                    icon = DatePickIcons.Kakao,
                    iconTint = Color.Unspecified
                ) {

                }

                Spacer(modifier = Modifier.height(10.dp))

                UnAccentButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    text = "다음에 할래요",
                ) {
                    navController.popBackStack()
                }

                Spacer(modifier = Modifier.navigationBarsHeight())

            }
        }

    }
}

@Composable
@Preview
fun LoginScreenPreview() {
    BaseTheme {
    }
}