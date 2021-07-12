package app.hdj.datepick.android.ui.components.screens.main.profile

import androidx.compose.animation.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.android.ui.components.dialog.login.LoginDialog
import app.hdj.datepick.android.ui.preview.FakeUserStateProvider
import app.hdj.datepick.android.ui.providers.LocalMeState
import app.hdj.datepick.android.ui.providers.ProvideBasicsForPreview
import app.hdj.datepick.ui.components.*
import app.hdj.datepick.ui.styles.DatePickTheme
import app.hdj.datepick.ui.utils.extract
import app.hdj.shared.client.domain.StateData
import app.hdj.shared.client.domain.entity.User

@OptIn(ExperimentalAnimationApi::class, ExperimentalFoundationApi::class)
@Composable
fun ProfileScreen(
    vm: ProfileViewModelDelegate = hiltViewModel<ProfileViewModel>(),
    onSettingClicked: () -> Unit = {}
) {

    val (state, effect, event) = vm.extract()
    val meState = LocalMeState.current

    val loginDialogState = rememberDialogState(initialState = false)

    TitledLazyListScaffold(
        title = { Text(text = "프로필") },
        expandedTitle = { LargeTitle(text = "프로필") },
        topAppBarActions = {
            IconButton(onClick = { onSettingClicked() }) {
                Icon(imageVector = Icons.Rounded.Settings, null)
            }
        }
    ) {

    }

    LoginDialog(loginDialogState)

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