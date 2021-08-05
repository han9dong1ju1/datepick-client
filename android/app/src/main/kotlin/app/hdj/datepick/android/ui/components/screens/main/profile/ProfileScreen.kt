package app.hdj.datepick.android.ui.components.screens.main.profile

import androidx.compose.animation.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.android.ui.components.dialog.login.LoginDialog
import app.hdj.datepick.android.ui.components.dialog.login.LoginViewModel
import app.hdj.datepick.android.ui.components.screens.AppNavigationGraph
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import app.hdj.datepick.android.ui.providers.LocalMe
import app.hdj.datepick.android.ui.providers.ProvideBasicsForPreview
import app.hdj.datepick.domain.isStateSucceed
import app.hdj.datepick.ui.components.*
import app.hdj.datepick.ui.styles.DatePickTheme
import app.hdj.datepick.ui.utils.extract

@OptIn(ExperimentalAnimationApi::class, ExperimentalFoundationApi::class)
@Composable
fun ProfileScreen(
    vm: ProfileViewModelDelegate = hiltViewModel<ProfileViewModel>(),
    onSettingClicked: () -> Unit = {}
) {

    val (state, effect, event) = vm.extract()

    val navController = LocalAppNavController.current
    val me = LocalMe.current

    TitledLazyListScaffold(
        title = { Text(text = "프로필") },
        expandedTitle = { LargeTitle(text = "프로필") },
        topAppBarActions = {
            IconButton(onClick = { onSettingClicked() }) {
                Icon(imageVector = Icons.Rounded.Settings, null)
            }
        }
    ) {
        item {
            DatePickButton(text = "로그인") {
                if (me == null) {
                    navController.navigate(AppNavigationGraph.LoginDialog.route)
                }
            }
        }
    }

}

@Composable
@Preview
fun ProfileScreenPreview(
) {
    DatePickTheme {
        ProvideBasicsForPreview {
            ProfileScreen(fakeProfileViewModel())
        }
    }
}