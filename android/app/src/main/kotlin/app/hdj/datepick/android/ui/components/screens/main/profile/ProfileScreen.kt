package app.hdj.datepick.android.ui.components.screens.main.profile

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.android.ui.components.screens.AppNavigationGraph
import app.hdj.datepick.android.ui.providers.LocalAppNavController
import app.hdj.datepick.android.ui.providers.LocalMe
import app.hdj.datepick.android.ui.providers.ProvideBasicsForPreview
import app.hdj.datepick.ui.components.*
import app.hdj.datepick.ui.styles.DatePickTheme
import app.hdj.datepick.ui.utils.extract
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalAnimationApi::class, ExperimentalFoundationApi::class)
@Composable
fun ProfileScreen(
    vm: ProfileViewModelDelegate = hiltViewModel<ProfileViewModel>(),
    onSettingClicked: () -> Unit = {}
) {

    val (state, effect, event) = vm.extract()

    val navController = LocalAppNavController.current
    val me = LocalMe.current

    DatePickScaffold(modifier = Modifier.fillMaxSize(), topBar = {
        DatePickTopAppBar(title = { Text("프로필") })
    }) {
        Column(modifier = Modifier.padding(top = it.calculateTopPadding())) {

            DatePickButton(text = "로그인") {
                navController.navigate(AppNavigationGraph.LoginDialog.route)
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