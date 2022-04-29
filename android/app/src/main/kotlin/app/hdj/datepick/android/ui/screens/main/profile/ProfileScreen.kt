package app.hdj.datepick.android.ui.screens.main.profile

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.android.ui.destinations.LoginBottomSheetDialogDestination
import app.hdj.datepick.android.ui.destinations.SettingsScreenDestination
import app.hdj.datepick.android.ui.screens.settings.SettingsScreen
import app.hdj.datepick.android.utils.extract
import app.hdj.datepick.presentation.profile.ProfileScreenViewModel
import app.hdj.datepick.presentation.profile.ProfileScreenViewModelDelegate
import app.hdj.datepick.ui.components.BaseButton
import app.hdj.datepick.ui.components.BaseScaffold
import app.hdj.datepick.ui.components.InsetTopBar
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Composable
@Destination
fun ProfileScreen(
    navigator: DestinationsNavigator
) {
    ProfileScreenContent(
        onSettingClicked = {
            navigator.navigate(SettingsScreenDestination)
        },
        onLoginClicked = {
            navigator.navigate(LoginBottomSheetDialogDestination)
        },
        vm = hiltViewModel<ProfileScreenViewModel>()
    )
}

@Composable
private fun ProfileScreenContent(
    onSettingClicked: () -> Unit = {},
    onLoginClicked: () -> Unit = {},
    vm: ProfileScreenViewModelDelegate
) {

    val (state, effect, event) = vm.extract()

    BaseScaffold(
        topBar = {
            InsetTopBar(
                title = {
                    Text("프로필")
                },
                actions = {
                    IconButton(onSettingClicked) {
                        Icon(imageVector = Icons.Rounded.Settings, null)
                    }
                }
            )
        }
    ) {

        LazyColumn(modifier = Modifier.padding(it)) {

            item {
                Text(text = state.me?.nickname.orEmpty())
            }

            item {
                BaseButton(text = "로그인하기", onClick = onLoginClicked)
            }

        }

    }

}