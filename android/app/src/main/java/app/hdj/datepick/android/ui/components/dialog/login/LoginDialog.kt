package app.hdj.datepick.android.ui.components.dialog.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.android.ui.icons.DatePickIcons
import app.hdj.datepick.android.ui.icons.Google
import app.hdj.datepick.android.ui.icons.Kakao
import app.hdj.datepick.ui.components.*
import app.hdj.datepick.ui.styles.DatePickTheme
import app.hdj.datepick.ui.utils.extract

@Composable
fun DialogScope.LoginDialogUi() {

    DialogUI {

        DialogTextContent(title = "로그인", message = "더 많은 기능을 사용하기 위해 로그인해주세요.")

        Spacer(modifier = Modifier.height(20.dp))

        /*
        * Login Buttons
        * */

        DatePickButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            border = BorderStroke(1.dp, MaterialTheme.colors.onSurface.copy(alpha = 0.1f)),
            text = "구글로 시작하기",
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.surface,
                contentColor = MaterialTheme.colors.onSurface,
            ),
            icon = DatePickIcons.Login.Google,
            iconTint = Color.Unspecified
        ) {

        }

        Spacer(modifier = Modifier.height(10.dp))

        DatePickButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            text = "카카오로 시작하기",
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFFFEE500),
                contentColor = Color(0xD9000000),
            ),
            icon = DatePickIcons.Login.Kakao,
            iconTint = Color.Unspecified
        ) {

        }

        Spacer(modifier = Modifier.height(10.dp))

        DatePickUnAccentButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            text = "다음에 할래요",
        ) {

        }

    }


}

@Composable
fun LoginDialog(
    dialogState: DialogState,
    vm: LoginViewModelDelegate = hiltViewModel<LoginViewModel>()
) {

    val (state, effect, event) = vm.extract()

    DatePickDialog(dialogState) {
        LoginDialogUi()
    }

}

@Composable
@Preview
fun LoginScreenPreview() {
    DatePickTheme {
        emptyDialogScope.LoginDialogUi()
    }
}