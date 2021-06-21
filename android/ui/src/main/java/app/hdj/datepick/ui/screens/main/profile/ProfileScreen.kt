package app.hdj.datepick.ui.screens.main.profile

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.ui.styles.DatePickTheme
import app.hdj.datepick.ui.utils.extract

@Composable
fun ProfileScreen(vm: ProfileViewModelDelegate = hiltViewModel<ProfileViewModel>()) {

    val (state, effect, event) = vm.extract()

}

@Composable
@Preview
fun ProfileScreenPreview() {
    DatePickTheme {
        ProfileScreen(fakeProfileViewModel())
    }
}