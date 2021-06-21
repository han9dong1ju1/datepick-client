package app.hdj.datepick.ui.screens.onboarding.register

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.ui.styles.DatePickTheme
import app.hdj.datepick.ui.utils.extract

@Composable
fun RegisterScreen(vm: RegisterViewModelDelegate = hiltViewModel<RegisterViewModel>()) {

    val (state, effect, event) = vm.extract()


}

@Composable
@Preview
fun RegisterScreenPreview() {
    DatePickTheme {
        RegisterScreen(fakeRegisterViewModel())
    }
}