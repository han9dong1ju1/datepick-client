package app.hdj.datepick.android.ui.components.screens.main.profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import app.hdj.datepick.android.ui.components.dialog.login.LoginDialog
import app.hdj.datepick.android.ui.preview.FakeUserStateProvider
import app.hdj.datepick.android.ui.providers.LocalMeState
import app.hdj.datepick.android.ui.providers.ProvideBasicsForPreview
import app.hdj.datepick.ui.components.DatePickTopAppBar
import app.hdj.datepick.ui.components.rememberDialogState
import app.hdj.datepick.ui.styles.DatePickTheme
import app.hdj.datepick.ui.utils.extract
import app.hdj.shared.client.domain.StateData
import app.hdj.shared.client.domain.entity.User
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.statusBarsPadding
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState

@Composable
fun ProfileScreen(
    vm: ProfileViewModelDelegate = hiltViewModel<ProfileViewModel>()
) {

    val (state, effect, event) = vm.extract()
    val meState = LocalMeState.current

    val loginDialogState = rememberDialogState(initialState = false)

    val collapsingToolbarScaffoldState = rememberCollapsingToolbarScaffoldState()

    CollapsingToolbarScaffold(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
        state = collapsingToolbarScaffoldState,
        scrollStrategy = ScrollStrategy.ExitUntilCollapsed,
        toolbar = {

            val textSize =
                (18 + (30 - 18) * collapsingToolbarScaffoldState.toolbarState.progress).sp

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .pin()
            )

            Text(
                text = "프로필",
                modifier = Modifier
                    .road(Alignment.CenterStart, Alignment.BottomStart),
                fontSize = textSize
            )

        }) {

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(100) {
                Text(
                    text = "Item $it",
                    modifier = Modifier.padding(8.dp)
                )
            }
        }

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