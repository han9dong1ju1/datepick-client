package app.hdj.datepick.android.ui.components.screens.main.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.ui.preview.FakeUserStateProvider
import app.hdj.datepick.ui.providers.LocalMeState
import app.hdj.datepick.ui.providers.ProvideBasicsForPreview
import app.hdj.datepick.ui.styles.DatePickTheme
import app.hdj.datepick.ui.utils.extract
import app.hdj.shared.client.domain.StateData
import app.hdj.shared.client.domain.entity.User

@Composable
fun ProfileScreen(vm: ProfileViewModelDelegate = hiltViewModel<ProfileViewModel>()) {

    val (state, effect, event) = vm.extract()
    val meState = LocalMeState.current

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        when (meState) {
            is StateData.Failed -> {
                meState.throwable

                Text(text = meState.cachedData?.name ?: "Failed")
            }
            is StateData.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(20.dp)
                        .padding(20.dp)
                        .align(Alignment.Center)
                )
            }
            is StateData.Success -> {
                Text(text = meState.data.name ?: "No Nickname")
            }
        }
    }

}

@Composable
@Preview
fun ProfileScreenPreview(
    @PreviewParameter(FakeUserStateProvider::class) userState: StateData<User>
) {
    DatePickTheme {
        ProvideBasicsForPreview(meState = userState) {
            ProfileScreen(fakeProfileViewModel())
        }
    }
}