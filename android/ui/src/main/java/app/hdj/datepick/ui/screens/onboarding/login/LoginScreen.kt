package app.hdj.datepick.ui.screens.onboarding.login

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.ui.styles.DatePickTheme
import app.hdj.datepick.ui.utils.extract

@Composable
fun LoginScreen(vm: LoginViewModelDelegate = hiltViewModel<LoginViewModel>()) {

    val (state, effect, event) = vm.extract()

}

@Composable
@Preview
fun LoginScreenPreview() {
    DatePickTheme {
        LoginScreen(fakeLoginViewModel())
    }
}