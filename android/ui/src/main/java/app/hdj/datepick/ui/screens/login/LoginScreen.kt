package app.hdj.datepick.ui.screens.login

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.ui.styles.DatePickTheme
import app.hdj.datepick.ui.utils.extract

@Composable
fun LoginScreen(loginViewModel: LoginViewModelDelegate = hiltViewModel<LoginViewModel>()) {

    val (state, effect, event) = loginViewModel.extract()

}

@Composable
@Preview
fun LoginScreenPreview() {
    DatePickTheme {
        LoginScreen(fakeLoginViewModel())
    }
}